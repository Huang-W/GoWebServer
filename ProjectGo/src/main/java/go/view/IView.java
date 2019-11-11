package go.view;

public interface IView {

	// run jar file and look at outputPanel
	// mouse event occurs in AppView
	void mouseEvent(int xCoord, int yCoord);
	
	// see CommandPanel "SetActionCommand()" line 29
	void actionEvent(String actionCommand);
	
	// Update view based on gameState from model
	void updateGameState(String gameState);
	
}
