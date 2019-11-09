package go.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

//import go.view.screen.GameScreen;
import go.view.screen.WelcomeScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AppController implements ActionListener {
	
	private static AppController theApp = null;
	public static final Dimension NORTH_DIM = new Dimension( 800, 200);
	public static final Dimension CENTER_DIM = new Dimension( 600, 600);
	public static final Dimension EAST_DIM = new Dimension( 200, 600);
	
	private JFrame appFrame;
	private Container welcomeScreen;
	//private Container gameScreen;
	private Container currentScreen;
	
	private JMenuBar menuBar;
	
	private AppController() { }
	
    public synchronized static AppController getInstance() {
        if (theApp == null) {
            return getNewInstance() ;
        }
        else
            return theApp;
    }
    
    public synchronized static AppController getNewInstance() {
        theApp = new AppController();
        theApp.startup();
        return theApp;
    }
	
	public void startup()
	{   
		appFrame = new JFrame();
		welcomeScreen = new WelcomeScreen(this);
		//gameScreen = new GameScreen();
		currentScreen = welcomeScreen;
		
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		JMenu menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
		
		appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		appFrame.setBackground(Color.GRAY);
		appFrame.setLayout(new BorderLayout());
		
        appFrame.setContentPane(currentScreen);
        appFrame.setJMenuBar(menuBar);
        appFrame.setResizable(false);
        appFrame.setLocationByPlatform(true);
        
        appFrame.pack();
        appFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand())
		{
			case "startNewGame":
				//currentScreen = gameScreen;
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
