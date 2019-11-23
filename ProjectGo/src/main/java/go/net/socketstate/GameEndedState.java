package go.net.socketstate;

public class GameEndedState implements GoWebSocketState {
    private GoWebSocketStateMachine stateMachine;

    public GameEndedState(GoWebSocketStateMachine stateMachine) {
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
        stateMachine.alert("The game is over! No more moves may be made.");
    }

    @Override
    public void handlePass() {
        stateMachine.alert("The game is over! No more moves may be made.");
    }

    @Override
    public void handleUndo() {
        stateMachine.alert("The game is over! No more moves may be made.");
    }
}
