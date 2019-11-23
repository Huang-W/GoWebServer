package go.net.socketstate;

import go.controller.GoMoveController;

public class InSinglePlayerGameState implements GoWebSocketState {
    private GoWebSocketStateMachine goWebSocketStateMachine;
    private GoMoveController goMoveController;

    public InSinglePlayerGameState(GoWebSocketStateMachine goWebSocketStateMachine, GoMoveController goMoveController) {
        this.goWebSocketStateMachine = goWebSocketStateMachine;
        this.goMoveController = goMoveController;
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        // wipe the board by just replacing this state with a new one so it has a new GoMoveController and a new board state
        this.goWebSocketStateMachine.joinSinglePlayerGame();
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        this.goWebSocketStateMachine.joinTwoPlayerGameQueue();
    }

    @Override
    public void handleMove(int x, int y) {
        assert (goMoveController != null);
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
