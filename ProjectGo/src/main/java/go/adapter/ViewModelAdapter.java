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

public class ViewModelAdapter implements GoViewObserver {

    private GoMoveController goMoveController;

    public ViewModelAdapter(GoMoveController goMoveController) {
        this.goMoveController = goMoveController;
    }


    @Override
    public void handleMouseClickEvent(Point point) {
        // TODO Auto-generated method stub
        int x = (int) point.getX();
        int y = (int) point.getY();
        goMoveController.makeNextPlayersMove(x,y);
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
