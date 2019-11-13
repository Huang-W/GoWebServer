package go.controller.impl;


import go.controller.GoMoveController;
import go.model.datamodel.GoGame;
import go.model.datamodel.GoPoint;
import go.model.datamodel.impl.GoGameImpl;
import go.model.observer.GoGameSubject;

public class GoMoveControllerImpl implements GoMoveController {
    private GoGame game;
    private GoGameSubject subject;

    public GoMoveControllerImpl() {
        GoGameImpl goGame = new GoGameImpl();
        this.game = goGame;
        this.subject = goGame;
    }
    @Override
    public void makeNextPlayersMove(GoPoint point) {
        game.makeMove(point);
    }


    @Override
    public void pass() {
        game.pass();
    }

    @Override
    public GoGameSubject getGameSubject() {
        return subject;
    }
}
