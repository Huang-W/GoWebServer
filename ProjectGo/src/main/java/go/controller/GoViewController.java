package go.controller;

import java.awt.Color;

import go.view.observer.GoViewSubject;

public interface GoViewController {
	
    /**
     * Draw a stone on the board
     * @param x xCoord of stone to draw (in pixels)
     * @param y yCoord of stone to draw (in pixels)
     * @param color Color of Stone to draw 
     */
    void drawStone(int x, int y, Color color);
    
    /**
     * Draw an empty space on the board
     * @param x xCoord of stone to remove (in pixels)
     * @param y yCoord of stone to remove (in pixels)
     */
    void drawEmptySpace(int x, int y);
    
    /**
     * Announce the winner of this game
     * @param color The color of the winner
     */
    void announceGameWinner(Color color);

    /**
     * Get a game subject for observation by a view.
     * @return the game to observe for results of moves passed to the controller
     */
    GoViewSubject getViewSubject();
    
}
