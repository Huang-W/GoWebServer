package go.view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WelcomeScreen extends AbstractButton {
	
	private final int NO_OPTIONS = 0;
	private final int BUTTON_DIM_X = 150;
	private final int BUTTON_DIM_Y = 100;
	private final int PANEL_SIZE = 200;
	private final int SCREEN_SIZE = 600;
	private final Dimension buttonDim = new Dimension(BUTTON_DIM_X, BUTTON_DIM_Y);
	private final Image bgImg = new ImageIcon("images/welcome.jpg").getImage();
	private final Border buttonBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.LIGHT_GRAY);
	
	private JComponent[][] welcomePanels;
	private List<JComponent> buttons;
	
	private JButton configNewGame;
	private JButton startNewGame;
	
	public WelcomeScreen() {
		this.setLayout( new GridBagLayout() );
		this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		
		welcomePanels = new JPanel[3][3];
		buttons = new ArrayList<JComponent>();
		initPanels();
		initButtons();
		
		// Fill in welcomeScreen panels
		addComponent(startNewGame, GridBagConstraints.LINE_START);
		addComponent(Box.createRigidArea(buttonDim), GridBagConstraints.CENTER);
		addComponent(configNewGame, GridBagConstraints.LINE_END);
		
        // Filler bottom component that will push the rest to the top
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(Box.createGlue(), gbc);
        
		configNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispatchEvent(e);
			}
		});
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("start a new game you fool" + e.toString());
				ActionEvent newEvent = new ActionEvent(WelcomeScreen.this, 
						ActionEvent.ACTION_PERFORMED, 
						e.getActionCommand(),
						System.currentTimeMillis(),
						NO_OPTIONS
				);
				System.out.println("start a new game you fool" + newEvent.toString());
				WelcomeScreen.this.dispatchEvent(newEvent);
			}
		});
	}
	
	private void addComponent(Component component, int gridBagConstraint)
	{
		switch(gridBagConstraint)
		{
		case GridBagConstraints.FIRST_LINE_START:
			welcomePanels[0][0].add(component);
			break;
		case GridBagConstraints.PAGE_START:
			welcomePanels[1][0].add(component);
			break;
		case GridBagConstraints.FIRST_LINE_END:
			welcomePanels[2][0].add(component);
			break;
		case GridBagConstraints.LINE_START:
			welcomePanels[0][1].add(component);
			break;
		case GridBagConstraints.CENTER:
			welcomePanels[1][1].add(component);
			break;
		case GridBagConstraints.LINE_END:
			welcomePanels[2][1].add(component);
			break;
		case GridBagConstraints.LAST_LINE_START:
			welcomePanels[0][2].add(component);
			break;
		case GridBagConstraints.PAGE_END:
			welcomePanels[1][2].add(component);
			break;
		case GridBagConstraints.LAST_LINE_END:
			welcomePanels[2][2].add(component);
			break;
		default:
			System.err.println("Invalid WelcomeScreen Constraint in " + this.getClass().getName() +
					" with GridBagConstraint of " + gridBagConstraint);
		}
	}
	
	private void initPanels()
	{
		GridBagConstraints c = new GridBagConstraints();
		EmptyBorder buttonBorder = new EmptyBorder((PANEL_SIZE - BUTTON_DIM_Y) / 2,
				(PANEL_SIZE - BUTTON_DIM_X) / 2,
				(PANEL_SIZE - BUTTON_DIM_Y) / 2,
				(PANEL_SIZE - BUTTON_DIM_X) / 2);
		
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
	
	private void initButtons()
	{
		configNewGame = new JButton("Config Start");
		startNewGame = new JButton("Quick Start");
		
		configNewGame.setActionCommand("configNewGame");
		startNewGame.setActionCommand("startNewGame");
		
		buttons.add(configNewGame);
		buttons.add(startNewGame);
		
		for (JComponent button : buttons)
		{
			button.setPreferredSize(buttonDim);
			button.setForeground(Color.RED);
			button.setBackground(Color.BLACK);
			button.setBorder(buttonBorder);
		}
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
}
