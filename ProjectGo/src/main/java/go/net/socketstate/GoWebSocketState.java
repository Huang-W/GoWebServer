package go.net.socketstate;

public interface GoWebSocketState {
    /**
     * Handles a message requesting to join a single-player game.
     */
    void handleJoinSinglePlayerGame();

    /**
     * Handles a message requesting to join a multi-player game.
     */
    void handleJoinTwoPlayerGame();

    /**
     * Handles a message describing a move to be made.
     * @param x the x position of the move
     * @param y the y position of the move
     */
    void handleMove(int x, int y);

    /**
     * Handle a request to pass.
     */
    void handlePass();

    /**
     * Handle a request to undo a move.
     */
    void handleUndo();
}
