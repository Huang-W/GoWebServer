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
        stateMachine.joinTwoPlayerGameQueue();
    }

    @Override
    public void handleMove(int x, int y) {
        stateMachine.alert("Join a game to get started.");
    }

    @Override
    public void handlePass() {
        stateMachine.alert("Join a game to get started.");
    }

    @Override
    public void handleUndo() {
        stateMachine.alert("Join a game to get started.");
    }
}
