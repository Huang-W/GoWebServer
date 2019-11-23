package go.view.screen.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WelcomeScreen extends GoScreenImpl {
	
	// Hardcoded variables for a 600x600 screen resolution
	private final int PANEL_SIZE = 200;
	private final int SCREEN_SIZE = 600;
	private final int PANELS_PER_ROW = 3;
	private final Dimension buttonDim = new Dimension(150, 100);
	private final Image bgImg = new ImageIcon("images/welcome.jpg").getImage();
	private final Border buttonBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, 
			Color.DARK_GRAY, Color.LIGHT_GRAY);
	
	// The WelcomeScreen is sectioned into a 3x3 grid of panels
	private JComponent[][] welcomePanels;
	private List<JComponent> buttons;
	
	private JButton configNewGame;
	private JButton startNewGame;
	
	/**
	 * Constructor
	 * Initialize buttons and the panels they reside in.
	 * Prettify display and setup Observer pattern to notify
	 * GoView of any user input
	 */
	public WelcomeScreen() {
		super();
		this.setLayout( new GridBagLayout() );
		this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		
		welcomePanels = new JPanel[PANELS_PER_ROW][PANELS_PER_ROW];
		buttons = new ArrayList<JComponent>();
		
		initPanels();
		initButtons();
		
		// Fill in welcomeScreen panels
		welcomePanels[0][1].add(startNewGame);
		welcomePanels[1][1].add(Box.createRigidArea(buttonDim));
		welcomePanels[2][1].add(configNewGame);
		
        // Filler bottom component that will push the rest to the top
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(Box.createGlue(), gbc);
	}
	
	/**
	 * Set up the 3x3 grid of panels for the welcomeScreen buttons
	 */
	private void initPanels()
	{
		GridBagConstraints c = new GridBagConstraints();
		EmptyBorder buttonBorder = new EmptyBorder((PANEL_SIZE - buttonDim.height) / 2,
				(PANEL_SIZE - buttonDim.width) / 2,
				(PANEL_SIZE - buttonDim.height) / 2,
				(PANEL_SIZE - buttonDim.width) / 2);
		
		for (int x = 0; x < 3; x ++)
			for (int y = 0; y < 3; y++) {
				welcomePanels[x][y] = new JPanel();
				welcomePanels[x][y].setLayout(new BorderLayout());
				welcomePanels[x][y].setBorder(buttonBorder);
				welcomePanels[x][y].setOpaque(false);
				c.gridx = x;
				c.gridy = y;
				this.add(welcomePanels[x][y], c);
			}
	}
	
	/*
	 * Initialize welcomeScreen buttons to notify GoView
	 * and update the model on user input
	 */
	private void initButtons()
	{
		configNewGame = new JButton("Config Start");
		startNewGame = new JButton("Quick Start");
		startNewGame.setOpaque(true);
		configNewGame.setOpaque(true);
		
		configNewGame.setActionCommand("CONFIG_START");
		startNewGame.setActionCommand("QUICK_START");
		
		buttons.add(configNewGame);
		buttons.add(startNewGame);
		
		for (JComponent button : buttons)
		{
			button.setPreferredSize(buttonDim);
			button.setForeground(Color.RED);
			button.setBackground(Color.BLACK);
			button.setBorder(buttonBorder);
		}
		
		configNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notifyObserversOfActionEvent(e);
			}
		});
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notifyObserversOfActionEvent(e);
			}
		});
	}
	
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
        size.width = Math.max(size.width, lmPrefSize.width);
        size.height = Math.max(size.height, lmPrefSize.height);
        return size;
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(bgImg, 0, 0, null);
      }

    /**
     * Not Used
     */
	@Override
	public void paintOval(Point location, Color color) {}

	/**
	 * Not Used
	 */
	@Override
	public void paintGrid(Point location) {}
}
