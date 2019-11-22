package go.view.screen;

import java.awt.Color;
import java.awt.Point;

/*
 * A Basic GoScreen interface with two main functions
 */
public interface GoScreen {
	
	/**
	 * Set Screen Visible
	 */
	void showScreen();
	
	/**
	 * Set Screen Not Visible
	 */
	void hideScreen();
	
	/**
	 * Draw an Oval centered at location
	 * @param location The location to draw the Oval
	 * @param color The color of the Oval
	 */
	void paintOval(Point location, Color color);
	
	/**
	 * Draw two lines centered at location
	 * @param location The location to draw the two lines
	 */
	void paintGrid(Point location);
}
