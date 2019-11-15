package go.adapter;

import java.awt.Point;

import go.controller.GoViewController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.*;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;

public class ModelViewAdapter implements GoGameObserver, GoMoveObserver, GoViewSubject {

    private GoViewController goViewController;
    private static final int BOARD_SIZE = 9;
    public static final int NUM_TILES = BOARD_SIZE - 1;
    public static final int TILE_SIZE = GoViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
    public static final int BORDER_SIZE = TILE_SIZE;

    @Override
    public void handleGameEnd(StoneColor winner) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addViewObserver(GoViewObserver observer) {
        goViewController.getViewSubject().addViewObserver(observer);
        // TODO Auto-generated method stub
        goViewController.getViewSubject().addViewObserver(observer);

    }

    @Override
    public void notifyObserversOfMouseClick(Point point) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfPassTurnRequest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfUndoMoveRequest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfWindowClose() {
        // TODO Auto-generated method stub

    }

}