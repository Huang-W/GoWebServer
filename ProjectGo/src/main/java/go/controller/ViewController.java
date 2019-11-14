package go.controller;

import java.awt.event.ActionListener;

public interface ViewController extends ActionListener {

	// run jar file and look at outputPanel
	// mouse event occurs in AppView
	void fireMouseEvent(int xCoord, int yCoord);
	
	// Give me a String or 2d array or w/e to display
	// Translate "getGameSubject" from "GoMoveController" for me please
	void updateGameState(String gameState);
	
}
