package go.net.socketstate;

import go.controller.GoMoveController;
import go.controller.impl.GoMoveControllerImpl;

public class InSinglePlayerGameState implements GoWebSocketState {
    private GoWebSocketStateMachine goWebSocketStateMachine;
    private GoMoveController goMoveController;

    public InSinglePlayerGameState(GoWebSocketStateMachine goWebSocketStateMachine) {
        this.goWebSocketStateMachine = goWebSocketStateMachine;
        goMoveController = new GoMoveControllerImpl();
        goMoveController.getGameSubject().addMoveObserver(goWebSocketStateMachine);
        goMoveController.getGameSubject().addGameObserver(goWebSocketStateMachine);
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        // wipe the board by just replacing this state with a new one so it has a new GoMoveController and a new board state
        this.goWebSocketStateMachine.joinSinglePlayerGame();
    }

    @Override
    public void handleJoinTwoPlayerGame() {

    }

    @Override
    public void handleMove(int x, int y) {
        goMoveController.makeNextPlayersMove(x, y);
    }

    @Override
    public void handlePass() {
        goMoveController.pass();
    }

    @Override
    public void handleUndo() {
        goMoveController.undo();
    }
}
