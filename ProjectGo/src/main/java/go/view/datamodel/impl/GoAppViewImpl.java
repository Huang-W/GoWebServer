package go.view.datamodel.impl;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.List;
import go.view.datamodel.GoMove;
import go.view.datamodel.GoAction;
import go.view.datamodel.GoAppView;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GoAppViewImpl extends JFrame implements GoAppView, GoViewSubject {
	
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 700, 700);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	private GameScreen gameScreen;
	private WelcomeScreen welcomeScreen;
	
	private final String[] options = {"Close App", "Restart", "Cancel"};
	private final int CLOSE_APP = 0;
	private final int RESTART_APP = 1;
	private final int CANCEL = 2;
	
    private List<GoViewObserver> viewObservers;
	
	public GoAppViewImpl() { 
		
		welcomeScreen = new WelcomeScreen();
		gameScreen = new GameScreen();
		gameScreen.setVisible(false);
		
		GoAppViewImpl.this.setBackground(Color.GRAY);
	    GoAppViewImpl.this.setLayout(new BorderLayout());
	    GoAppViewImpl.this.add(gameScreen, BorderLayout.NORTH);
	    GoAppViewImpl.this.add(welcomeScreen, BorderLayout.SOUTH);
	    GoAppViewImpl.this.setResizable(false);
	    GoAppViewImpl.this.setLocationByPlatform(true);
	    GoAppViewImpl.this.pack();
	    GoAppViewImpl.this.setVisible(true);
	    
	    GoAppViewImpl.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		GoAppViewImpl.this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (gameScreen.isVisible()) {
					int chosenOption = JOptionPane.showOptionDialog(GoAppViewImpl.this,
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
			    GoAppViewImpl.this.setVisible(false);
			    GoAppViewImpl.this.dispose();
			}
	    });
		gameScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action... " + e.toString());
				switch(e.getActionCommand())
				{
				case "passTurn":
					notifyObserversOfButtonPress(GoAction.PASS);
					break;
				case "undoMove":
					notifyObserversOfButtonPress(GoAction.UNDO);
					break;
				default:
					System.out.println("Unhandled Action Event: " + e.getActionCommand());
				}
			}
		});
		welcomeScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action... " + e.toString());
				switch(e.getActionCommand())
				{
					case "startNewGame":
						showGameScreen();
						break;
					case "configNewGame":
						break;
					default:
						System.err.println("Action Command " + e.getActionCommand() + 
								" in " + getClass().getName() );
						return;
				}
			}
		});
	}
	
	private void showGameScreen() {
		gameScreen.setVisible(true);
		welcomeScreen.setVisible(false);
		validate();
		pack();
	}
	
	private void showWelcomeScreen() {
		gameScreen.setVisible(false);
		welcomeScreen.setVisible(true);
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
	public void notifyObserversOfButtonPress(GoAction action) {
		viewObservers.forEach(observer -> observer.handleButtonPressEvent(action));
	}
	
}
