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