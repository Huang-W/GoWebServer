package go.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppView implements ActionListener {
	
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 600, 600);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	private JFrame appFrame;
	private Container welcomeScreen;
	private Container gameScreen;
	private Container currentScreen;
	
	public AppView() { }
	
	public void startup()
	{   
		appFrame = new JFrame();
		welcomeScreen = new WelcomeScreen(this);
		gameScreen = new GameScreen();
		currentScreen = welcomeScreen;
		
		appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		appFrame.setBackground(Color.GRAY);
		appFrame.setLayout(new BorderLayout());
		
        appFrame.setContentPane(currentScreen);
        
        appFrame.setResizable(false);
        appFrame.setLocationByPlatform(true);
        appFrame.pack();
        appFrame.setVisible(true);
        appFrame.addMouseListener(new MouseAdapter() {
        	
        	@Override
			public void mousePressed(MouseEvent e) {
        		//make sure click is within boardPanel
				currentScreen.dispatchEvent(e);
			}
        	
        });
       
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand())
		{
			case "startNewGame":
				currentScreen = gameScreen;
				break;
			default:
				System.err.println("Invalid Action Command " + e.getActionCommand() + 
						" in " + getClass().getName() );
				return;
		}
		
		appFrame.setContentPane(currentScreen);
		appFrame.pack();
		appFrame.setVisible(true);
		
	}
	
}
