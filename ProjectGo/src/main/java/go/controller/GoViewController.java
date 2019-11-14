package go.controller;

import java.awt.Color;
import java.awt.event.ActionListener;

import go.view.observer.GoViewSubject;

public interface GoViewController {

    /**
     * Draw a stone on the board
     * @param x xCoord of stone to draw (in pixels)
     * @param y yCoord of stone to draw (in pixels)
     * @param color Color of Stone to draw 
     */
    void setStone(int x, int y, Color color);
    
    /**
     * Draw an empty space on the board
     * @param x xCoord of stone to remove (in pixels)
     * @param y yCoord of stone to remove (in pixels)
     */
    void removeStone(int x, int y);

    /**
     * Get a game subject for observation by a view.
     * @return the game to observe for results of moves passed to the controller
     */
    GoViewSubject getViewSubject();
    
}
