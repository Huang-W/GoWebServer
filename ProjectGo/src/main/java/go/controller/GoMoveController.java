package go.controller;

import go.model.observer.GoGameSubject;

/**
 * An interface for access to the Go game model by views.
 * GoMoveControllers are used to route moves to the model and
 * provide access to the game subject for observing.
 */
public interface GoMoveController {
    /**
     * Make a move for the next player.
     * Notify observers of the results of the move.
     * @param x the x location on the go board of the point the stone was added to
     * @param y the y location on the go board of the point the stone was added to
     * @return true if the move was legal, false if the move was illegal and cannot be made
     */
    boolean makeNextPlayersMove(int x, int y);

    /**
     * Pass for the next player.
     * Both players passing in a row results in the game ending.
     */
    void pass();

    /**
     * Undoes the previous player's move.
     */
    void undo();

    /**
     * Empty the GameBoard
     */
    void resetGameBoard();

    /**
     * Reconfigure the BoardSize
     * @param boardSize The given board dimensions to set
     */
    void configureBoardSize(int boardSize);
    
    /**
     * Get a game subject for observation by a view.
     * @return the game to observe for results of moves passed to the controller
     */
    GoGameSubject getGameSubject();

}
