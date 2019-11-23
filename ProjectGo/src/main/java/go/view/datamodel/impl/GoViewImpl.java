package go.view.datamodel.impl;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.util.LinkedList;
import java.util.List;

import go.view.datamodel.GoMove;
import go.view.datamodel.GoView;
import go.view.observer.GoScreenObserver;
import go.view.observer.GoViewConfigObserver;
import go.view.observer.GoViewConfigSubject;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.view.screen.controller.ScreenController;
import go.view.screen.controller.impl.ScreenControllerImpl;
import go.view.screen.impl.ConfigScreen;
import go.view.screen.impl.GameScreen;
import go.view.screen.impl.GoScreenImpl;
import go.view.screen.impl.WelcomeScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@SuppressWarnings("serial")
public class GoViewImpl extends JFrame implements GoView, GoViewSubject, GoScreenObserver, GoViewConfigSubject {
	
	private File audioFile = new File("audio/gostone.wav");
	
	private ConfigScreen configScreen;
	private GoScreenImpl currentScreen;
	private GoScreenImpl gameScreen;
	private GoScreenImpl welcomeScreen;
	private ScreenController configScreenController;
	private ScreenController gameScreenController;
	private ScreenController welcomeScreenController;
	
	private final String[] options = {"Close App", "Restart", "Cancel"};
	private final int CLOSE_APP = 0;
	private final int RESTART_APP = 1;
	private final int CANCEL = 2;
	
	private final String PASS = "PASS";
	private final String UNDO = "UNDO";
	private final String QUICK_START = "QUICK_START";
	private final String CONFIG_START = "CONFIG_START";
	private final String SET_BOARD_SIZE_NINE = "SET_BOARD_SIZE_NINE";
	private final String SET_BOARD_SIZE_THIRTEEN = "SET_BOARD_SIZE_THIRTEEN";
	private final String SET_BOARD_SIZE_NINETEEN = "SET_BOARD_SIZE_NINETEEN";
	
    private List<GoViewObserver> viewObservers;
    private List<GoViewConfigObserver> viewConfigObservers;
    
	public GoViewImpl() { 
		
		viewObservers = new LinkedList<>();
		viewConfigObservers = new LinkedList<>();
		
		configScreen = new ConfigScreen();
		gameScreen = new GameScreen();
		welcomeScreen = new WelcomeScreen();
		currentScreen = welcomeScreen;
		
		configScreenController = new ScreenControllerImpl(configScreen);
		gameScreenController = new ScreenControllerImpl(gameScreen);
		welcomeScreenController = new ScreenControllerImpl(welcomeScreen);
		
		configScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		gameScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		welcomeScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		
		GoViewImpl.this.setBackground(Color.GRAY);
	    GoViewImpl.this.setLayout(new BorderLayout());
	    GoViewImpl.this.add(gameScreen, BorderLayout.NORTH);
	    GoViewImpl.this.add(welcomeScreen, BorderLayout.SOUTH);
	    GoViewImpl.this.add(configScreen, BorderLayout.EAST);
	    GoViewImpl.this.setResizable(false);
	    GoViewImpl.this.setLocationByPlatform(true);
	    
		showWelcomeScreen();
	    
	    GoViewImpl.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		GoViewImpl.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (welcomeScreen.equals(currentScreen) == false) {
					int chosenOption = JOptionPane.showOptionDialog(GoViewImpl.this,
							"Would you like to close the app or start a new game?",
							"Confirm Close",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[CLOSE_APP]);
					if (chosenOption == RESTART_APP) {
						GoViewImpl.this.notifyObserversOfWindowCloseEvent();
						showWelcomeScreen();
						return;
					}
					else if (chosenOption == CANCEL) {
						return;
					}
				}
			    GoViewImpl.this.setVisible(false);
			    GoViewImpl.this.dispose();
			}
	    });
	}

	@Override
	public void handleActionEvent(ActionEvent event) {
		switch(event.getActionCommand())
		{
		case QUICK_START:
			if (ConfigScreen.BOARD_SIZE() != 9)
				notifyObserversOfScreenSizeConfigEvent(9);
			showGameScreen();
			break;
		case PASS:
			notifyObserversOfPassTurnRequest();
			break;
		case UNDO:
			notifyObserversOfUndoMoveRequest();
			break;
		case CONFIG_START:
			showConfigScreen();
			break;
		case SET_BOARD_SIZE_NINE:
			notifyObserversOfScreenSizeConfigEvent(9);
			showGameScreen();
			break;
		case SET_BOARD_SIZE_THIRTEEN:
			notifyObserversOfScreenSizeConfigEvent(13);
			showGameScreen();
			break;
		case SET_BOARD_SIZE_NINETEEN:
			notifyObserversOfScreenSizeConfigEvent(19);
			showGameScreen();
		default:
			System.out.println("I dont how know to throw the exceptions");
		}
	}

	@Override
	public void handleMouseEvent(MouseEvent event) {
		Point point = new Point(event.getX(), event.getY());
		notifyObserversOfMouseClick(point);
		System.out.println("xCoord: " + event.getX() + " yCoord: " + event.getY());
	}
	
	@Override
	public void drawStone(GoMove move) {
		currentScreen.paintOval(move.getPoint(), move.getStoneColor());
		playAudioClip();
	}

	@Override
	public void drawEmptySpace(Point location) {
		currentScreen.paintGrid(location);
	}

	@Override
	public void announceGameWinner(Color color) {
		String winner = color.equals(Color.BLACK) ? "BLACK" : "WHITE";
		if (gameScreen.equals(currentScreen)) {
			int chosenOption = JOptionPane.showOptionDialog(GoViewImpl.this,
					winner + " Wins!\n" + 
					"Would you like to close the app or start a new game?",
					winner + " Wins!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[CLOSE_APP]);
			if (chosenOption == RESTART_APP) {
				GoViewImpl.this.notifyObserversOfWindowCloseEvent();
				showWelcomeScreen();
				return;
			}
			else if (chosenOption == CANCEL) {
				return;
			}
		}
	    GoViewImpl.this.setVisible(false);
	    GoViewImpl.this.dispose();
	}
	
	@Override
	public void configureBoardSize(int boardSize) {
		System.out.println("configure board size in goview");
		configScreen.setBoardSize(boardSize);
	}

	private void showGameScreen() {
		currentScreen = gameScreen;
		welcomeScreenController.hideScreen();
		configScreenController.hideScreen();
		gameScreenController.showScreen();
		validate();
		pack();
	    GoViewImpl.this.setVisible(true);
	}

	private void showWelcomeScreen() {
		currentScreen = welcomeScreen;
		configScreenController.hideScreen();
		gameScreenController.hideScreen();
		welcomeScreenController.showScreen();
		validate();
		pack();
	    GoViewImpl.this.setVisible(true);
	}
	
	private void showConfigScreen() {
		currentScreen = configScreen;
		gameScreenController.hideScreen();
		welcomeScreenController.hideScreen();
		configScreenController.showScreen();
		validate();
		pack();
	    GoViewImpl.this.setVisible(true);
	}
	
	private void playAudioClip() {
		try {
			Clip audioClip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
	        audioClip.open(ais);
	        audioClip.start();
	        audioClip.wait(150);
	        audioClip.stop();
	        audioClip.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void notifyObserversOfMouseClick(Point point) {
		viewObservers.forEach(observer -> observer.handleMouseClickEvent(point));
	}

	@Override
	public void notifyObserversOfPassTurnRequest() {
		viewObservers.forEach(observer -> observer.handlePassTurnRequest());
	}

	@Override
	public void notifyObserversOfUndoMoveRequest() {
		viewObservers.forEach(observer -> observer.handleUndoMoveRequest());
	}

	@Override
	public void notifyObserversOfWindowCloseEvent() {
		viewObservers.forEach(observer -> observer.handleWindowClose());
	}

	@Override
	public void notifyObserversOfScreenSizeConfigEvent(int boardSize) {
		viewConfigObservers.forEach(observer -> observer.handleBoardSizeConfigure(boardSize));
	}

	@Override
	public void addViewObserver(GoViewObserver observer) {
		viewObservers.add(observer);
	}
	
	@Override
	public void addViewConfigObserver(GoViewConfigObserver observer) {
		viewConfigObservers.add(observer);
	}
}
