package go.net.socketstate;

import go.controller.GoMoveController;

public class TwoPlayerGameMyTurnState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;
    private GoMoveController moveController;

    public TwoPlayerGameMyTurnState(GoWebSocketStateMachine stateMachine, GoMoveController moveController) {
        this.stateMachine = stateMachine;
        this.moveController = moveController;
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        stateMachine.leaveMultiPlayerGame();
        stateMachine.joinSinglePlayerGame();
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        stateMachine.leaveMultiPlayerGame();
        stateMachine.joinTwoPlayerGameQueue();
    }

    @Override
    public void handleMove(int x, int y) {
        if (moveController.makeNextPlayersMove(x, y)) {
            stateMachine.multiPlayerMoveMade();
        }
    }

    @Override
    public void handlePass() {
        stateMachine.multiPlayerMoveMade();
        moveController.pass();
    }

    @Override
    public void handleUndo() {
        stateMachine.alert("You may not undo a move in multiplayer games.");
    }
}
