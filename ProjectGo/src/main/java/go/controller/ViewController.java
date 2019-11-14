package go.controller;

public interface ViewController {

	// run jar file and look at outputPanel
	// mouse event occurs in AppView
	void mouseEvent(int xCoord, int yCoord);
	
	// see CommandPanel "SetActionCommand()" line 29
	void actionEvent(String actionCommand);
	
	// Give me a String or 2d array or w/e to display
	// Translate "getGameSubject" from "GoMoveController" for me please
	void updateGameState(String gameState);
	
}
