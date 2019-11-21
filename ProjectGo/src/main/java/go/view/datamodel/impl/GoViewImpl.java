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
import go.view.screen.controller.ScreenController;
import go.view.screen.controller.impl.ScreenControllerImpl;
import go.view.screen.impl.GameScreen;
import go.view.screen.impl.GoScreenImpl;
import go.view.screen.impl.WelcomeScreen;

import java.awt.GridBagConstraints;
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
	
	private GoScreenImpl currentScreen;
	private GoScreenImpl gameScreen;
	private GoScreenImpl welcomeScreen;
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
	
	private final int FULL_TILE_LENGTH = BoardPanel.TILE_SIZE;
	private final int HALF_TILE_LENGTH = BoardPanel.TILE_SIZE/2;
	
    private List<GoViewObserver> viewObservers;
	
	public GoViewImpl() { 
		viewObservers = new LinkedList<GoViewObserver>();
		
		gameScreen = new GameScreen();
		welcomeScreen = new WelcomeScreen();
		currentScreen = welcomeScreen;
		
		gameScreenController = new ScreenControllerImpl(gameScreen);
		welcomeScreenController = new ScreenControllerImpl(welcomeScreen);
		
		gameScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		welcomeScreenController.getGoScreenSubject().registerGoScreenObserver(this);
		
		GoViewImpl.this.setBackground(Color.GRAY);
	    GoViewImpl.this.setLayout(new BorderLayout());
	    GoViewImpl.this.add(gameScreen, BorderLayout.NORTH);
	    GoViewImpl.this.add(welcomeScreen, BorderLayout.SOUTH);
	    GoViewImpl.this.setResizable(false);
	    GoViewImpl.this.setLocationByPlatform(true);
	    
		showWelcomeScreen();
	    
	    GoViewImpl.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		GoViewImpl.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (gameScreen.equals(currentScreen)) {
					int chosenOption = JOptionPane.showOptionDialog(GoViewImpl.this,
							"Would you like to close the app or start a new game?",
							"Confirm Close",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[CLOSE_APP]);
					if (chosenOption == RESTART_APP) {
						GoViewImpl.this.notifyObserversOfWindowClose();
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
		g.setColor(BoardPanel.BG_COLOR);
		g.fillRect(move.getPoint().x - HALF_TILE_LENGTH, 
				move.getPoint().y - HALF_TILE_LENGTH, 
				FULL_TILE_LENGTH, FULL_TILE_LENGTH);
		g.setColor(move.getStoneColor());
		g.fillOval(move.getPoint().x - HALF_TILE_LENGTH, 
				move.getPoint().y - HALF_TILE_LENGTH, 
				FULL_TILE_LENGTH, FULL_TILE_LENGTH);
	}

	@Override
	public void drawEmptySpace(Point location) {
		System.out.println("Drawing empty space " + location.toString());
		Graphics g = ((Component) gameScreenController.getGoScreenSubject()).getGraphics();
		g.setColor(BoardPanel.BG_COLOR);
		g.fillRect(location.x - HALF_TILE_LENGTH, 
				location.y - HALF_TILE_LENGTH, 
				FULL_TILE_LENGTH, FULL_TILE_LENGTH);
		g.setColor(Color.BLACK);
		if (isCornerLocated(location))
			drawEmptyCornerSpace(g, location, getCardinalCorner(location));
		else if (isEdgeLocated(location))
			drawEmptyEdgeSpace(g, location, getCardinalEdge(location));
		else
			drawEmptyInnerSpace(g, location);
	}

	private void drawEmptyCornerSpace(Graphics g, Point location, int cardinalDirection) {
		System.out.println("Drawing empty corner space");
		switch(cardinalDirection)
		{
		case GridBagConstraints.NORTHWEST:
			//left to right
			g.drawLine(location.x, location.y, 
					location.x + HALF_TILE_LENGTH, location.y);
			//top to bottom
			g.drawLine(location.x, location.y, 
					location.x, location.y + HALF_TILE_LENGTH);
			break;
		case GridBagConstraints.NORTHEAST:
			//left to right
			g.drawLine(location.x - HALF_TILE_LENGTH, location.y, 
					location.x, location.y);
			//top to bottom
			g.drawLine(location.x, location.y, 
					location.x, location.y + HALF_TILE_LENGTH);
			break;
		case GridBagConstraints.SOUTHWEST:
			//top to bottom
			g.drawLine(location.x, location.y - HALF_TILE_LENGTH, 
					location.x, location.y);
			//left to right
			g.drawLine(location.x, location.y, 
					location.x + HALF_TILE_LENGTH, location.y);
			break;
		case GridBagConstraints.SOUTHEAST:
			//left to right
			g.drawLine(location.x - HALF_TILE_LENGTH, location.y, 
					location.x, location.y);
			//top to bottom
			g.drawLine(location.x, location.y - HALF_TILE_LENGTH, 
					location.x, location.y);
			break;
		default:
			System.err.println("CardinalDirection not supported in drawEmptyCornerSpace");
		}
	}

	private void drawEmptyEdgeSpace(Graphics g, Point location, int cardinalDirection) {
		System.out.println("Drawing Edge Space X: " + location.x + " Y: " + location.y + " Dir: " + cardinalDirection);
		switch(cardinalDirection)
		{
		case GridBagConstraints.WEST:
			//top to bottom
			g.drawLine(location.x, location.y - HALF_TILE_LENGTH, 
					location.x, location.y + HALF_TILE_LENGTH);
			//left to right
			g.drawLine(location.x, location.y, 
					location.x + HALF_TILE_LENGTH, location.y);
			break;
		case GridBagConstraints.EAST:
			//top to bottom
			g.drawLine(location.x, location.y - HALF_TILE_LENGTH, 
					location.x, location.y + HALF_TILE_LENGTH);
			//left to right
			g.drawLine(location.x - HALF_TILE_LENGTH, location.y, 
					location.x, location.y);
			break;
		case GridBagConstraints.NORTH:
			//left to right
			g.drawLine(location.x - HALF_TILE_LENGTH, location.y, 
					location.x + HALF_TILE_LENGTH, location.y);
			//top to bottom
			g.drawLine(location.x, location.y, 
					location.x, location.y + HALF_TILE_LENGTH);
			break;
		case GridBagConstraints.SOUTH:
			//left to right
			g.drawLine(location.x - HALF_TILE_LENGTH, location.y, 
					location.x + HALF_TILE_LENGTH, location.y);
			//top to bottom
			g.drawLine(location.x, location.y - HALF_TILE_LENGTH, 
					location.x, location.y);
			break;
		default:
			System.err.println("CardinalDirection not supported in drawEmptyEdgeSpace");
		}
	}

	private void drawEmptyInnerSpace(Graphics g, Point location) {
		g.drawLine(location.x - HALF_TILE_LENGTH, location.y,
				location.x + HALF_TILE_LENGTH, location.y);
		g.drawLine(location.x, location.y - HALF_TILE_LENGTH,
				location.x, location.y + HALF_TILE_LENGTH);
	}

	private boolean isCornerLocated(Point location) {
		int xCoord = location.x / FULL_TILE_LENGTH;
		int yCoord = location.y / FULL_TILE_LENGTH;
		if (xCoord == 1 && yCoord == 1)
			return true;
		if (xCoord == 1 && yCoord == BoardPanel.BOARD_SIZE)
			return true;
		if (xCoord == BoardPanel.BOARD_SIZE && yCoord == 1)
			return true;
		if (xCoord == BoardPanel.BOARD_SIZE && yCoord == BoardPanel.BOARD_SIZE)
			return true;
		return false;
	}

	private boolean isEdgeLocated(Point location) {
		System.out.println("Is Edge Located");
		int xCoord = location.x / FULL_TILE_LENGTH;
		int yCoord = location.y / FULL_TILE_LENGTH;
		if (xCoord == 1 || xCoord == BoardPanel.BOARD_SIZE ||
				yCoord == 1 || yCoord == BoardPanel.BOARD_SIZE)
			return true;
		return false;
	}

	private int getCardinalCorner(Point location) {
		int xCoord = location.x / FULL_TILE_LENGTH;
		int yCoord = location.y / FULL_TILE_LENGTH;
		if (xCoord == 1 && yCoord == 1)
			return GridBagConstraints.NORTHWEST;
		if (xCoord == 1 && yCoord == BoardPanel.BOARD_SIZE)
			return GridBagConstraints.SOUTHWEST;
		if (xCoord == BoardPanel.BOARD_SIZE && yCoord == 1)
			return GridBagConstraints.NORTHEAST;
		if (xCoord == BoardPanel.BOARD_SIZE && yCoord == BoardPanel.BOARD_SIZE)
			return GridBagConstraints.SOUTHEAST;
		return -1;
	}

	private int getCardinalEdge(Point location) {
		int xCoord = location.x / FULL_TILE_LENGTH;
		int yCoord = location.y / FULL_TILE_LENGTH;
		if (xCoord == 1)
			return GridBagConstraints.WEST;
		if (xCoord == BoardPanel.BOARD_SIZE)
			return GridBagConstraints.EAST;
		if (yCoord == 1)
			return GridBagConstraints.NORTH;
		if (yCoord == BoardPanel.BOARD_SIZE)
			return GridBagConstraints.SOUTH;
		return -1;
	}

	@Override
	public void handleActionEvent(ActionEvent event) {
		switch(event.getActionCommand())
		{
		case QUICK_START:
			showGameScreen();
			break;
		case PASS:
			notifyObserversOfPassTurnRequest();
			break;
		case UNDO:
			notifyObserversOfUndoMoveRequest();
			break;
		case CONFIG_START:
			System.out.println("Should we implement this or not?");
			break;
		default:
			System.out.println("I don't know how to throw exceptions");
		}
	}

	@Override
	public void handleMouseEvent(MouseEvent event) {
		Point point = new Point(event.getX(), event.getY());
		notifyObserversOfMouseClick(point);
		System.out.println("xCoord: " + event.getX() + " yCoord: " + event.getY());
	}
	
	private void showGameScreen() {
		currentScreen = gameScreen;
		gameScreenController.showScreen();
		welcomeScreenController.hideScreen();
		validate();
		pack();
	    GoViewImpl.this.setVisible(true);
	}

	private void showWelcomeScreen() {
		currentScreen = welcomeScreen;
		gameScreenController.hideScreen();
		welcomeScreenController.showScreen();
		validate();
		pack();
	    GoViewImpl.this.setVisible(true);
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

	@Override
	public void announceGameWinner(Color color, double points) {
		if (gameScreen.equals(currentScreen)) {
			int chosenOption = JOptionPane.showOptionDialog(GoViewImpl.this,
					color.toString() + " wins by " + points + " points.\n" + 
					"Would you like to close the app or start a new game?",
					color.toString() + " Wins!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[CLOSE_APP]);
			if (chosenOption == RESTART_APP) {
				GoViewImpl.this.notifyObserversOfWindowClose();
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

}
