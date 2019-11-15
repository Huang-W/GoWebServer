package go.view.datamodel.impl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.util.LinkedList;
import java.util.List;

import go.view.datamodel.GoMove;
import go.view.datamodel.GoView;
import go.view.observer.GoScreenObserver;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.view.panel.BoardPanel;
import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;
import go.view.screen.controller.ScreenController;
import go.view.screen.controller.impl.ScreenControllerImpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GoViewImpl extends JFrame implements GoView, GoViewSubject, GoScreenObserver {
	
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 700, 700);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	private ScreenController gameScreenController;
	private ScreenController welcomeScreenController;
	private Component currentScreen;
	
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
		viewObservers = new LinkedList<GoViewObserver>();
		gameScreenController = new ScreenControllerImpl(new GameScreen());
		gameScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		welcomeScreenController = new ScreenControllerImpl(new WelcomeScreen());
		welcomeScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		currentScreen = (Component) welcomeScreenController.getGoScreenSubject();
		
		GoViewImpl.this.setBackground(Color.GRAY);
	    GoViewImpl.this.setLayout(new BorderLayout());
	    GoViewImpl.this.add((Component) gameScreenController.getGoScreenSubject(), BorderLayout.NORTH);
	    GoViewImpl.this.add((Component) welcomeScreenController.getGoScreenSubject(), BorderLayout.SOUTH);
	    GoViewImpl.this.setResizable(false);
	    GoViewImpl.this.setLocationByPlatform(true);
		showWelcomeScreen();
	    GoViewImpl.this.setVisible(true);
	    
	    GoViewImpl.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		GoViewImpl.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (currentScreen.isVisible()) {
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
	
	@Override
	public void drawStone(GoMove move) {
		Graphics g = ((Component) gameScreenController.getGoScreenSubject()).getGraphics();
		g.setColor(move.getStoneColor());
		g.fillOval(move.getPoint().x - BoardPanel.TILE_SIZE/2, 
				move.getPoint().y - BoardPanel.TILE_SIZE/2, 
				BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
	}
	
	@Override
	public void drawEmptySpace(Point location) {
		Graphics g = ((Component) gameScreenController.getGoScreenSubject()).getGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(location.x - BoardPanel.TILE_SIZE/2,
				location.y - BoardPanel.TILE_SIZE/2,
				BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
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
		Point point = new Point(event.getX(), event.getY());
		notifyObserversOfMouseClick(point);
		
		// temp code to test drawing
		Graphics g = ((Component) gameScreenController.getGoScreenSubject()).getGraphics();
		g.fillOval(event.getX() - BoardPanel.TILE_SIZE/2, 
				event.getY() - BoardPanel.TILE_SIZE/2, 
				BoardPanel.TILE_SIZE, BoardPanel.TILE_SIZE);
	}
	
	private void showGameScreen() {
		gameScreenController.showScreen();
		welcomeScreenController.hideScreen();
		validate();
		pack();
	}
	
	private void showWelcomeScreen() {
		gameScreenController.hideScreen();
		welcomeScreenController.showScreen();
		validate();
		pack();
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
	public void notifyObserversOfWindowClose() {
		viewObservers.forEach(observer -> observer.handleWindowClose());
	}
	
	@Override
	public void addViewObserver(GoViewObserver observer) {
		viewObservers.add(observer);
	}
	
}
