package go.adapter;

import java.awt.Color;

import go.controller.GoViewController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;

public class ModelViewAdapter implements GoGameObserver, GoMoveObserver {

    public ModelViewAdapter(GoViewController goViewController) {
        this.goViewController = goViewController;
    }

    private int BOARD_SIZE = 9;
    private int NUM_TILES = BOARD_SIZE - 1;
    private int TILE_SIZE = 700 / (NUM_TILES + 2);
    private int BORDER_SIZE = TILE_SIZE = 70;

    private GoViewController goViewController;

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        int x = move.getPoint().getX() * TILE_SIZE + BORDER_SIZE;
        int y = move.getPoint().getY() * TILE_SIZE + BORDER_SIZE;
        Color color = move.getStoneColor().equals(StoneColor.BLACK) ? Color.BLACK : Color.WHITE;
        goViewController.drawStone(x, y, color);
        System.out.println("col: " + x + " row: " + y);
    }
    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        int x = point.getX() * TILE_SIZE + BORDER_SIZE;
        int y = point.getY() * TILE_SIZE + BORDER_SIZE;
    	System.out.println("Removing a piece in adapter X: " + x + " Y: " + y);
        goViewController.drawEmptySpace(x, y);
    }

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

}

