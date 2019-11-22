package go.model.datamodel;

/**
 * A game of Go.
 * Records the board state and handles moves.
 */
public interface GoGame {
    /**
     * Make a single move for the next player.
     * @param move the location the next player paced a stone
     */
    void makeMove(GoPoint move);

    /**
     * Pass for the next player.
     * If both players pass in a row, the game ends, and
     * {@link go.model.observer.GoGameObserver}s will be notified of the winner.
     */
    void pass();

    /**
     * Empty the board of stones
     */
    void reset();
    
    /**
     * Set the dimensions of the new board
     * @param size The size to set the dimensions to
     */
    void configureBoardSize(int size);
}
