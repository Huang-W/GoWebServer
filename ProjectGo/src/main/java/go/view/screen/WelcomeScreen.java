package go.view.screen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class WelcomeScreen extends JLabel {
	
	private final int BUTTON_DIM_X = 150;
	private final int BUTTON_DIM_Y = 100;
	private final int PANEL_SIZE = 200;
	private final int SCREEN_SIZE = 600;
	private final Dimension buttonDim = new Dimension(BUTTON_DIM_X, BUTTON_DIM_Y);
	private final ImageIcon bgImg = new ImageIcon("images/welcome.png");
	
	private JPanel[][] welcomePanels;
	
	private JButton configNewGame;
	private JButton startNewGame;
	private JButton createSgf;
	private JButton editSgf;
	private JButton reviewOldGame;
	
	public WelcomeScreen(ActionListener listener) {
		this.setIcon(bgImg);
		this.setLayout( new GridBagLayout() );
		this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		
		welcomePanels = new JPanel[3][3];
		initPanels();
		
		configNewGame = new JButton("configNewGame");
		startNewGame = new JButton("startNewGame");
		createSgf = new JButton("createSgf");
		editSgf = new JButton("editSgf");
		reviewOldGame = new JButton("reviewOldGame");
		
		configNewGame.setActionCommand("configNewGame");
		startNewGame.setActionCommand("startNewGame");
		createSgf.setActionCommand("createSgf");
		editSgf.setActionCommand("editSgf");
		reviewOldGame.setActionCommand("reviewOldGame");
		
		configNewGame.setPreferredSize(buttonDim);
		startNewGame.setPreferredSize(buttonDim);
		createSgf.setPreferredSize(buttonDim);
		editSgf.setPreferredSize(buttonDim);
		reviewOldGame.setPreferredSize(buttonDim);

		// add buttons to WelcomeScreen panels
		addButton(configNewGame, GridBagConstraints.PAGE_START);
		addButton(startNewGame, GridBagConstraints.CENTER);
		addButton(editSgf, GridBagConstraints.LINE_START);
		addButton(createSgf, GridBagConstraints.LINE_END);
		addButton(reviewOldGame, GridBagConstraints.PAGE_END);
		
        // Filler bottom component that will push the rest to the top
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(Box.createGlue(), gbc);
        
		configNewGame.addActionListener(listener);
		startNewGame.addActionListener(listener);
        
	}
	
	private void addButton(JButton button, int gridBagConstraint)
	{
		switch(gridBagConstraint)
		{
		case GridBagConstraints.FIRST_LINE_START:
			welcomePanels[0][0].add(button);
			break;
		case GridBagConstraints.PAGE_START:
			welcomePanels[1][0].add(button);
			break;
		case GridBagConstraints.FIRST_LINE_END:
			welcomePanels[2][0].add(button);
			break;
		case GridBagConstraints.LINE_START:
			welcomePanels[0][1].add(button);
			break;
		case GridBagConstraints.CENTER:
			welcomePanels[1][1].add(button);
			break;
		case GridBagConstraints.LINE_END:
			welcomePanels[2][1].add(button);
			break;
		case GridBagConstraints.LAST_LINE_START:
			welcomePanels[0][2].add(button);
			break;
		case GridBagConstraints.PAGE_END:
			welcomePanels[1][2].add(button);
			break;
		case GridBagConstraints.LAST_LINE_END:
			welcomePanels[2][2].add(button);
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
	
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
        size.width = Math.max(size.width, lmPrefSize.width);
        size.height = Math.max(size.height, lmPrefSize.height);
        return size;
    }
}
