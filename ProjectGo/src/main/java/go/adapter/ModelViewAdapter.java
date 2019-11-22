package go.adapter;

import java.awt.Color;

import go.controller.GoViewController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoModelConfigObserver;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;
import go.view.screen.impl.ConfigScreen;

/**
 * This class translates game coordinates from the game model into pixel coordinates for the game view
 */
public class ModelViewAdapter implements GoGameObserver, GoMoveObserver, GoModelConfigObserver {

	private final int boardScreenSize = ConfigScreen.CenterDim().height;
	
	private int boardSize = 9;
	private int numTiles = boardSize - 1;
	private int borderSize = boardScreenSize / (numTiles + 2);
	private int tileSize = borderSize;
	
    private GoViewController goViewController;

    /**
     * Constructor
     * @param goViewController Where the game model state updates are forwarded to
     */
     public ModelViewAdapter(GoViewController goViewController) {
         this.goViewController = goViewController;
     }

    /**
    * Lets the game view know where to place a new stone
    * @param move A GoMove that contains both the coordinates and the color of the new stone
    */
    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        int x = move.getPoint().getX() * tileSize + borderSize;
        int y = move.getPoint().getY() * tileSize + borderSize;
        Color color = move.getStoneColor().equals(StoneColor.BLACK) ? Color.BLACK : Color.WHITE;
        goViewController.drawStone(x, y, color);
        System.out.println("Placing a stone in adapter X: " + x + " Y: " + y);
    }
    
    /**
    * Lets the game view know where to remove a stone
    * @param point A point that contains the coordinates of which stone to remove
    */
    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        int x = point.getX() * tileSize + borderSize;
        int y = point.getY() * tileSize + borderSize;
    	System.out.println("Removing a piece in adapter X: " + x + " Y: " + y);
        goViewController.drawEmptySpace(x, y);
    }

    /**
    * Lets the view know that the game has ended
    * @param winner The color of the winning player
    */
    @Override
    public void handleGameEnd(StoneColor winner) {
    	Color winnerColor = StoneColor.BLACK.equals(winner) ? 
    			Color.BLACK : Color.WHITE;
    	
    	goViewController.announceGameWinner(winnerColor);
    	
        if(winnerColor.equals(Color.BLACK))
            System.out.println("Black is the winner!");
        else
            System.out.println("White is the winner!");
    }

	@Override
	public void handleBoardSizeChange(int boardSize) {
		this.boardSize = boardSize;
		this.numTiles = boardSize - 1;
		this.borderSize = boardScreenSize / (numTiles + 2);
		this.tileSize = borderSize;
		goViewController.updateBoardSize(boardSize);
	}

}

