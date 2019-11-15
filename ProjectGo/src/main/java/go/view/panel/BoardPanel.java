package go.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import go.view.datamodel.impl.GoAppViewImpl;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private static final int BOARD_SIZE = 9;
	public static final int NUM_TILES = BOARD_SIZE - 1;
	public static final int TILE_SIZE = GoAppViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
	public static final int BORDER_SIZE = TILE_SIZE;
	
	Border border;
	Insets insets;
	
	// initialize off-screen
	private int xCoordLastMove = -100;
	private int yCoordLastMove = -100;
	
	
	public BoardPanel()
	{
		this.setPreferredSize(GoAppViewImpl.CENTER_DIM);
		border = new EmptyBorder( BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.ORANGE);
		this.setBorder(border);
		insets = border.getBorderInsets(this);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				xCoordLastMove = e.getX();
				yCoordLastMove = e .getY();
				repaint(xCoordLastMove - TILE_SIZE/2, yCoordLastMove - TILE_SIZE/2, TILE_SIZE, TILE_SIZE);
				System.out.println("Point clicked at X: " + xCoordLastMove +
						" Y: " + yCoordLastMove);
			}
		});
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
	    	g2.drawString(xCoord, i * TILE_SIZE + BORDER_SIZE, BORDER_SIZE / 2);
	    	g2.drawString(xCoord, i * TILE_SIZE + BORDER_SIZE, 3 * BORDER_SIZE / 2 + TILE_SIZE * NUM_TILES);
	    	xCoord = String.valueOf((char) (chVal + 1));
	    }
	    // Draw yCoord System
	    String yCoord = "1";
	    for (int i = BOARD_SIZE; i > 0; i--) {
	    	int chVal = yCoord.charAt(0);
	    	g2.drawString(yCoord, 
	    			BORDER_SIZE / 2, (i - 1) * TILE_SIZE + BORDER_SIZE);
	    	g2.drawString(yCoord, 
	    			3 * BORDER_SIZE / 2 + TILE_SIZE * NUM_TILES, (i - 1) * TILE_SIZE + BORDER_SIZE);
	    	yCoord = String.valueOf((char) (chVal + 1));
	    }
	    
	    drawStarPoints(g2, 5);
	    g2.fillOval(xCoordLastMove - TILE_SIZE/2, yCoordLastMove - TILE_SIZE/2, TILE_SIZE, TILE_SIZE);
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