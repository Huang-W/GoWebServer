package go.controller;

import go.model.datamodel.GoPoint;
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
     * @param point the location the move was placed
     */
    void makeNextPlayersMove(GoPoint point);

    /**
     * Pass for the next player.
     * Both players passing in a row results in the game ending.
     */
    void pass();

    /**
     * Get a game subject for observation by a view.
     * @return the game to observe for results of moves passed to the controller
     */
    GoGameSubject getGameSubject();
}
