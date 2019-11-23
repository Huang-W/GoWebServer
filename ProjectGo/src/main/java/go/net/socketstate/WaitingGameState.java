package go.net.socketstate;

/**
 * A game state in which messages are discarded. Sockets in such a state
 * are waiting for other users - either for other users to join the multiplayer queue
 * or for other users to make a move.
 */
public class WaitingGameState implements GoWebSocketState {
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
