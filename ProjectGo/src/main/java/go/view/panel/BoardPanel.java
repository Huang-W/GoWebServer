package go.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import go.view.screen.impl.ConfigScreen;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	Border border;
	Insets insets;
	
	public BoardPanel()
	{
		this.setPreferredSize(ConfigScreen.CenterDim());
		border = new EmptyBorder( ConfigScreen.BORDER_SIZE(), ConfigScreen.BORDER_SIZE(), 
				ConfigScreen.BORDER_SIZE(), ConfigScreen.BORDER_SIZE());
		this.setLayout(new BorderLayout());
		this.setBackground(Color.ORANGE);
		this.setBorder(border);
		insets = border.getBorderInsets(this);
	}
	
	@Override
	/**
	 * Initialize the Board with Tiles and a Coordinate System
	 * Defaulted with Top-Left of (1,1)
	 * Bottom-Right of (9,9)
	 */
	protected void paintComponent(Graphics g) {
		
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);

	    g2.setColor(Color.BLACK);
	    // Draw rows.
	    for (int i = 0; i < ConfigScreen.BOARD_SIZE(); i++) {
	        g2.drawLine(ConfigScreen.BORDER_SIZE(), i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE(), 
	        		ConfigScreen.TILE_SIZE() * ConfigScreen.NUM_TILES() + ConfigScreen.BORDER_SIZE(), 
	        		i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE());
	    }
	    // Draw columns.
	    for (int i = 0; i < ConfigScreen.BOARD_SIZE(); i++) {
	        g2.drawLine(i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE(), ConfigScreen.BORDER_SIZE(), 
	        		i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE(), 
	        		ConfigScreen.TILE_SIZE() * ConfigScreen.NUM_TILES() + ConfigScreen.BORDER_SIZE());
	    }
	    // Draw xCoord System
	    String xCoord = "";
	    for (int i = 0; i < ConfigScreen.BOARD_SIZE(); i++) {
	    	xCoord = "" + (i + 1);
	    	g2.drawString(xCoord, 
	    			i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE(), ConfigScreen.BORDER_SIZE() / 4);
	    	g2.drawString(xCoord, 
	    			i * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE(), 
	    			7 * ConfigScreen.BORDER_SIZE() / 4 + ConfigScreen.TILE_SIZE() * ConfigScreen.NUM_TILES());
	    }
	    // Draw yCoord System
	    String yCoord = "";
	    for (int i = ConfigScreen.BOARD_SIZE(); i > 0; i--) {
	    	yCoord = "" + i;
	    	g2.drawString(yCoord, 
	    			ConfigScreen.BORDER_SIZE() / 4, (i - 1) * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE());
	    	g2.drawString(yCoord, 
	    			7 * ConfigScreen.BORDER_SIZE() / 4 + ConfigScreen.TILE_SIZE() * ConfigScreen.NUM_TILES(), 
	    			(i - 1) * ConfigScreen.TILE_SIZE() + ConfigScreen.BORDER_SIZE());
	    }
	}
}
