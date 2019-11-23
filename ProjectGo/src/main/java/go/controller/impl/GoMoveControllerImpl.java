package go.controller.impl;


import go.controller.GoMoveController;
import go.model.datamodel.GoGame;
import go.model.datamodel.impl.GoGameImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.model.gameplay.GoCapture;
import go.model.gameplay.GoScoringStrategy;
import go.model.observer.GoGameSubject;

public class GoMoveControllerImpl implements GoMoveController {
    private GoGame game;
    private GoGameSubject subject;
    
    public GoMoveControllerImpl() {
        this(new GoGameImpl());
    }
    public GoMoveControllerImpl(GoCapture capture, GoScoringStrategy scoringStrategy) {
        this(new GoGameImpl(capture, scoringStrategy));
    }
    public GoMoveControllerImpl(GoGameImpl goGame) {
        this.game = goGame;
        this.subject = goGame;
    }

    @Override
    public void resetGameBoard(){
        game.reset();
    }

    @Override
    public boolean makeNextPlayersMove(int x, int y) {
        System.out.printf("GoMoveController: make move %d, %d\n", x, y);
        return game.makeMove(GoPointImpl.of(x, y));
    }

    @Override
    public void pass() {
        System.out.println("GoMoveController: pass");
        game.pass();
    }

    @Override
    public void undo() {
        System.out.println("GoMoveController: undo");
        game.undo();
    }

    @Override
	public void configureBoardSize(int boardSize) {
		game.configureBoardSize(boardSize);
	}

    @Override
    public GoGameSubject getGameSubject() {
        return subject;
    }
}
