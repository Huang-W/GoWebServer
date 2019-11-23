package go.net.socketstate;

public class PendingTwoPlayerGameState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;

    public PendingTwoPlayerGameState(GoWebSocketStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        // do nothing, waiting on another player
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        // do nothing, waiting on another player
    }

    @Override
    public void handleMove(int x, int y) {
        // do nothing, waiting on another player
    }

    @Override
    public void handlePass() {
        // do nothing, waiting on another player
    }

    @Override
    public void handleUndo() {
        // do nothing, waiting on another player
    }
}
