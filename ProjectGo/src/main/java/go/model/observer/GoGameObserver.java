package go.model.observer;

import go.model.datamodel.StoneColor;

/**
 * An interface for objects that listen to when the Go game ends.
 * Implementations define what to do when they are notified
 * of the winner of a game.
 * Implementations can register themselves to be notified at {@link GoGameSubject}
 */
public interface GoGameObserver {
    /**
     * Handle the game ending.
     * @param winner the color of the player who won
     */
    void handleGameEnd(StoneColor winner);
}
