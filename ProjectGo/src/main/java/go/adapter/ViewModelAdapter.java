package go.adapter;

import java.awt.Point;

import go.controller.GoMoveController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoGameObserver;
import go.model.observer.GoGameSubject;
import go.model.observer.GoMoveObserver;
import go.view.observer.GoViewObserver;
import go.view.datamodel.impl.GoViewImpl;


public class ViewModelAdapter implements GoViewObserver {

    private GoMoveController goMoveController;

    public ViewModelAdapter(GoMoveController goMoveController) {
        this.goMoveController = goMoveController;
    }
    private static final int BOARD_SIZE = 9;
    public static final int NUM_TILES = BOARD_SIZE - 1;
    public static final int TILE_SIZE = GoViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
    public static final int BORDER_SIZE = TILE_SIZE;

    @Override
    public void handleMouseClickEvent(Point point) {
        // TODO Auto-generated method stub
        int x = translateCoordinate(point.x);
        int y = translateCoordinate(point.y);
        System.out.println(x);
        System.out.println(y);
        goMoveController.makeNextPlayersMove(x,y);
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


    @Override
    public void handlePassTurnRequest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleUndoMoveRequest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleWindowClose() {
        // TODO Auto-generated method stub

    }

}
