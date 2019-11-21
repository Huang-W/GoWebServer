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

import go.view.datamodel.impl.GoViewImpl;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	public static final int BOARD_SIZE = 9;
	public static final int NUM_TILES = BOARD_SIZE - 1;
	public static final int TILE_SIZE = GoViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
	public static final int BORDER_SIZE = TILE_SIZE;
	public static final Color BG_COLOR = Color.ORANGE;
	
	Border border;
	Insets insets;
	
	public BoardPanel()
	{
		this.setPreferredSize(GoViewImpl.CENTER_DIM);
		border = new EmptyBorder( BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
		this.setLayout(new BorderLayout());
		this.setBackground(BG_COLOR);
		this.setBorder(border);
		insets = border.getBorderInsets(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);

	    g2.setColor(Color.BLACK);
	    // Draw rows.
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        g2.drawLine(BORDER_SIZE, i * TILE_SIZE + BORDER_SIZE, TILE_SIZE
	                * NUM_TILES + BORDER_SIZE, i * TILE_SIZE + BORDER_SIZE);
	    }
	    // Draw columns.
	    for (int i = 0; i < BOARD_SIZE; i++) {
	        g2.drawLine(i * TILE_SIZE + BORDER_SIZE, BORDER_SIZE, i * TILE_SIZE
	                + BORDER_SIZE, TILE_SIZE * NUM_TILES + BORDER_SIZE);
	    }
	    // Draw xCoord System
	    String xCoord = "A";
	    for (int i = 0; i < BOARD_SIZE; i++) {
	    	int chVal = xCoord.charAt(0);
	    	g2.drawString(xCoord, 
	    			i * TILE_SIZE + BORDER_SIZE, BORDER_SIZE / 4);
	    	g2.drawString(xCoord, 
	    			i * TILE_SIZE + BORDER_SIZE, 7 * BORDER_SIZE / 4 + TILE_SIZE * NUM_TILES);
	    	xCoord = String.valueOf((char) (chVal + 1));
	    }
	    // Draw yCoord System
	    String yCoord = "";
	    for (int i = BOARD_SIZE; i > 0; i--) {
	    	yCoord = "" + i;
	    	g2.drawString(yCoord, 
	    			BORDER_SIZE / 4, (i - 1) * TILE_SIZE + BORDER_SIZE);
	    	g2.drawString(yCoord, 
	    			7 * BORDER_SIZE / 4 + TILE_SIZE * NUM_TILES, (i - 1) * TILE_SIZE + BORDER_SIZE);
	    }
	    
	    drawStarPoints(g2, 5);
	}
	
	// draws little points on the board, will do something about this later
	private void drawStarPoints(Graphics2D g2, int pointSize)
	{
		int p2 = 2 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		int p3 = 3 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		int p4 = 4 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		int p6 = 6 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		int p9 = 9 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		int p15 = 15 * TILE_SIZE + BORDER_SIZE - Math.floorDiv(pointSize, 2);
		switch (BOARD_SIZE)
		{
		case 9:
			g2.fillOval( p2, p2, pointSize, pointSize);
			g2.fillOval( p2, p6, pointSize, pointSize);
			g2.fillOval( p6, p2, pointSize, pointSize);
			g2.fillOval( p6, p6, pointSize, pointSize);
			g2.fillOval( p4, p4, pointSize, pointSize);
			break;
		case 13:
			g2.fillOval( p3, p3, pointSize, pointSize);
			g2.fillOval( p3, p9, pointSize, pointSize);
			g2.fillOval( p9, p3, pointSize, pointSize);
			g2.fillOval( p9, p9, pointSize, pointSize);
			g2.fillOval( p6, p6, pointSize, pointSize);
			break;
		case 19:
			g2.fillOval( p3, p3, pointSize, pointSize);
			g2.fillOval( p3, p9, pointSize, pointSize);
			g2.fillOval( p3, p15, pointSize, pointSize);
			g2.fillOval( p9, p3, pointSize, pointSize);
			g2.fillOval( p9, p9, pointSize, pointSize);
			g2.fillOval( p9, p15, pointSize, pointSize);
			g2.fillOval( p15, p3, pointSize, pointSize);
			g2.fillOval( p15, p9, pointSize, pointSize);
			g2.fillOval( p15, p15, pointSize, pointSize);
		}
	}

}
