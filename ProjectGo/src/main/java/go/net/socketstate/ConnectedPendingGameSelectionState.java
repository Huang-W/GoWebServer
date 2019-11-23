package go.net.socketstate;

public class ConnectedPendingGameSelectionState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;

    public ConnectedPendingGameSelectionState(GoWebSocketStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void handleJoinSinglePlayerGame() {
        stateMachine.joinSinglePlayerGame();
    }

    @Override
    public void handleJoinTwoPlayerGame() {
        stateMachine.joinTwoPlayerGame();
    }

    @Override
    public void handleMove(int x, int y) {
        // Do nothing
    }

    @Override
    public void handlePass() {
        // Do nothing
    }

    @Override
    public void handleUndo() {
        // Do nothing
    }
}
