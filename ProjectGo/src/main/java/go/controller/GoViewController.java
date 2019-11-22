package go.controller;

import java.awt.Color;

import go.view.observer.GoViewConfigSubject;
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
     * Update the Go View's definition of the current BoardSize
     * @param boardSize the size of the new board
     */
    void updateBoardSize(int boardSize);
    
    /**
     * Get a view subject for observation by the model.
     * @return the view to observe for user input passed to the controller
     */
    GoViewSubject getViewSubject();
    
    /**
     * Get a configuration subject for observation by the model
     * @return the configuration that will be observed for changes to the Game
     */
    GoViewConfigSubject getViewConfigSubject();
    
}
