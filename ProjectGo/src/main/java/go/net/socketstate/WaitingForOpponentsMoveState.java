package go.net.socketstate;

public class WaitingForOpponentsMoveState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;

    public WaitingForOpponentsMoveState(GoWebSocketStateMachine stateMachine) {
        this.stateMachine = stateMachine;
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
        stateMachine.alert("Please wait for your opponent to make their move.");
    }

    @Override
    public void handlePass() {
        stateMachine.alert("Please wait for your opponent to make their move.");
    }

    @Override
    public void handleUndo() {
        stateMachine.alert("Please wait for your opponent to make their move.");
    }
}
