package go.adapter;

import java.awt.Point;

import go.controller.GoMoveController;
import go.view.observer.GoViewConfigObserver;
import go.view.observer.GoViewObserver;
import go.view.screen.impl.ConfigScreen;

/**
 * This class translates mouse clicks sent from the GoView screens
 * into game coordinates that can be used by the game model
 * This class forwards pass and undo requests to the game model
 * This class forwards game configuration requests to the game model
 */
public class ViewModelAdapter implements GoViewObserver, GoViewConfigObserver {
    
	private final int boardScreenSize = ConfigScreen.CenterDim().height;
	
	private int boardSize = 9;
	private int numTiles = boardSize - 1;
	private int borderSize = boardScreenSize / (numTiles + 2);
	private int tileSize = borderSize;
	
    private GoMoveController goMoveController;
    
    /**
    * Constructor
    * @param goMoveController The controller to forward translated messages to
    */
    public ViewModelAdapter(GoMoveController goMoveController) {
        this.goMoveController = goMoveController;
    }

    /**
    * This notifies the game model of translated screen coordinates
    * @param point The point where the mouse was clicked
    */
    @Override
    public void handleMouseClickEvent(Point point) {
        int x = translateCoordinate(point.x);
        int y = translateCoordinate(point.y);
        System.out.println(x);
        System.out.println(y);

        goMoveController.makeNextPlayersMove(x,y);
    }

    @Override
    public void handlePassTurnRequest() {
        goMoveController.pass();
    }

    @Override
    public void handleUndoMoveRequest() {
        goMoveController.undo();
    }

    @Override
    public void handleWindowClose() {
        goMoveController.resetGameBoard();
    }
    
	@Override
	public void handleBoardSizeConfigure(int boardSize) {
		this.boardSize = boardSize;
		this.numTiles = boardSize - 1;
		this.borderSize = boardScreenSize / (numTiles + 2);
		this.tileSize = borderSize;
		goMoveController.configureBoardSize(boardSize);
	}
	
    /**
    * This formula calculates the pixels and translates it to game coordinates
    * @param as an integer for the pixel coordinate.  
    */
    private int translateCoordinate( int pixelCoord ) {
    	int tail = 0;
    	while (tail * tileSize < pixelCoord) {
    		tail += 1;
    	}
    	int head = tail - 1;
    	if (tail * tileSize - pixelCoord > pixelCoord - head * tileSize)
    		return head - 1;
    	return tail - 1;
    }

}
