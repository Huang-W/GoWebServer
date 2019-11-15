
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

public class ViewModelAdapter implements GoViewObserver, GoGameSubject {

    private GoMoveController goMoveController;

    @Override
    public void handleMouseClickEvent(Point point) {
        // TODO Auto-generated method stub

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

    @Override
    public void addMoveObserver(GoMoveObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addGameObserver(GoGameObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfPiecePlacement(GoMove move) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfPieceRemoval(GoPoint point) {
        // TODO Auto-generated method stub

    }

    @Override
    public void notifyObserversOfGameEnd(StoneColor winner) {
        // TODO Auto-generated method stub

    }

}
