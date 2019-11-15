package go.view.datamodel.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.EventListenerList;

import java.util.List;
import go.view.datamodel.GoView;
import go.view.observer.GoScreenObserver;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;
import go.view.screen.controller.ScreenController;
import go.view.screen.controller.impl.ScreenControllerImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GoViewImpl extends JFrame implements GoView, GoViewSubject, GoScreenObserver {
	
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 700, 700);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	private GameScreen gameScreen;
	private WelcomeScreen welcomeScreen;
	
	private ScreenController gameScreenController;
	private ScreenController welcomeScreenController;
	
	private final String[] options = {"Close App", "Restart", "Cancel"};
	private final int CLOSE_APP = 0;
	private final int RESTART_APP = 1;
	private final int CANCEL = 2;
	
	public static final String PASS = "PASS";
	public static final String UNDO = "UNDO";
	public static final String QUICK_START = "QUICK_START";
	public static final String CONFIG_START = "CONFIG_START";
	
    private List<GoViewObserver> viewObservers;
	
	public GoViewImpl() { 
		
		gameScreen = new GameScreen();
		gameScreenController = new ScreenControllerImpl(gameScreen);
		gameScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		
		welcomeScreen = new WelcomeScreen();
		welcomeScreenController = new ScreenControllerImpl(welcomeScreen);
		welcomeScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		
		GoViewImpl.this.setBackground(Color.GRAY);
	    GoViewImpl.this.setLayout(new BorderLayout());
	    GoViewImpl.this.add(gameScreen, BorderLayout.NORTH);
	    GoViewImpl.this.add(welcomeScreen, BorderLayout.SOUTH);
	    GoViewImpl.this.setResizable(false);
	    GoViewImpl.this.setLocationByPlatform(true);
		showWelcomeScreen();
	    GoViewImpl.this.setVisible(true);
	    
	    GoViewImpl.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		GoViewImpl.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (gameScreen.isVisible()) {
					int chosenOption = JOptionPane.showOptionDialog(GoViewImpl.this,
							"Would you like to close the app or start a new game?",
							"Confirm Close",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[CLOSE_APP]);
					if (chosenOption == RESTART_APP) {
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
	
	private void showGameScreen() {
		gameScreenController.displaySelectedComponent();
		welcomeScreenController.hideSelectedComponent();
		validate();
		pack();
	}
	
	private void showWelcomeScreen() {
		gameScreenController.hideSelectedComponent();
		welcomeScreenController.displaySelectedComponent();
		validate();
		pack();
	}
	
	@Override
	public void setStone(Point location, Color color) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeStone(Point location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addViewObserver(GoViewObserver observer) {
		viewObservers.add(observer);
	}

	@Override
	public void notifyObserversOfMouseClick(Point point) {
		viewObservers.forEach(observer -> observer.handleMouseClickEvent(point));
	}

	@Override
	public void notifyObserversOfPassTurnRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserversOfUndoMoveRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserversOfWindowClose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleActionEvent(ActionEvent event) {
		switch(event.getActionCommand())
		{
		case QUICK_START:
			showGameScreen();
			break;
		}
	}

	@Override
	public void handleMouseEvent(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}
