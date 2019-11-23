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
        // do nothing - already in game, and we don't want to abandon the other player :(
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        // do nothing - already in game, and we don't want to abandon the other player :(
    }

    @Override
    public void handleMove(int x, int y) {
        moveController.makeNextPlayersMove(x, y);
        stateMachine.multiPlayerMoveMade();
    }

    @Override
    public void handlePass() {
        moveController.pass();
        stateMachine.multiPlayerMoveMade();
    }

    @Override
    public void handleUndo() {
        // do nothing - undo is not allowed in multiplayer!
    }
}
