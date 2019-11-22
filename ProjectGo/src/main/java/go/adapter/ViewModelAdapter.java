package go.adapter;

import java.awt.Point;

import go.controller.GoMoveController;
import go.view.observer.GoViewObserver;

public class ViewModelAdapter implements GoViewObserver {

    private int BOARD_SIZE = 9;
    private int NUM_TILES = BOARD_SIZE - 1;
    private int TILE_SIZE = 700 / (NUM_TILES + 2);
    private int BORDER_SIZE = TILE_SIZE = 70;
    
    private GoMoveController goMoveController;

    public ViewModelAdapter(GoMoveController goMoveController) {
        this.goMoveController = goMoveController;
    }


    @Override
    public void handleMouseClickEvent(Point point) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

    }

    @Override
    public void handleWindowClose() {
        // TODO Auto-generated method stub

    }
    
    private int translateCoordinate( int pixelCoord ) {
    	int tail = 0;
    	while (tail * TILE_SIZE < pixelCoord) {
    		tail += 1;
    	}
    	int head = tail - 1;
    	if (tail * TILE_SIZE - pixelCoord > pixelCoord - head * TILE_SIZE)
    		return head - 1;
    	return tail - 1;
    }

}
