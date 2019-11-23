package go.net.socketstate;

import go.net.MultiPlayerGameMatchMaker;

/**
 * A game state in which messages are discarded. Sockets in such a state
 * are waiting for other users - either for other users to join the multiplayer queue
 * or for other users to make a move.
 */
public class WaitingForOtherPlayerToJoinState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;

    public WaitingForOtherPlayerToJoinState(GoWebSocketStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        MultiPlayerGameMatchMaker.getInstance().enterQueueOrLeaveIfAlreadyEnqueued(stateMachine);
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        stateMachine.alert("You are already enqueued. Waiting on another player to join.");
    }

    @Override
    public void handleMove(int x, int y) {
        stateMachine.alert("Waiting on another player to join.");
    }

    @Override
    public void handlePass() {
        stateMachine.alert("Waiting on another player to join.");
    }

    @Override
    public void handleUndo() {
        stateMachine.alert("Waiting on another player to join.");
    }
}
