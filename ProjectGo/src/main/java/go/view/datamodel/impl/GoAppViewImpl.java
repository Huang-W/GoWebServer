package go.view.datamodel.impl;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.util.ArrayList;
import java.util.List;
import go.view.datamodel.GoMove;
import go.view.datamodel.GoAppView;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class GoAppViewImpl extends JFrame implements GoAppView, GoViewSubject, ActionListener {
	
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 700, 700);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	//private JFrame appFrame;
	private WelcomeScreen welcomeScreen;
	private GameScreen gameScreen;
	
	private String[] options = {"Close App", "Restart", "Cancel"};
	private final int CLOSE_APP = 0;
	private final int RESTART_APP = 1;
	
    private List<GoViewObserver> viewObservers;
	
	public GoAppViewImpl() { 
		welcomeScreen = new WelcomeScreen(this);
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
	    
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showOptionDialog(GoAppViewImpl.this,
						"Would you like to close the app or start a new game?",
						"Confirm Close",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);
				System.out.println("answer... " + n);
				if (n == CLOSE_APP) {
				    GoAppViewImpl.this.setVisible(false);
				    GoAppViewImpl.this.dispose();
				} else if (n == RESTART_APP) {
					showWelcomeScreen();
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
	public void actionPerformed(ActionEvent e) {
		System.out.println("ACtion... " + e.toString());
		switch(e.getActionCommand())
		{
			case "startNewGame":
				showGameScreen();
			default:
				System.err.println("Invalid Action Command " + e.getActionCommand() + 
						" in " + getClass().getName() );
				return;
		}
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
	public void notifyObserversOfButtonPress(ActionEvent event) {
		viewObservers.forEach(observer -> observer.handleButtonPressEvent(event));
	}
	
}
