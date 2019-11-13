package go.model.observer;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;

/**
 * An interface for objects that listen to Go Move events.
 * Implementations define what to do when they are notified of
 * additions and removals of pieces on the Go board.
 * Implementations can register themselves to be notified at {@link GoGameSubject}
 */
public interface GoMoveObserver {
    /**
     * Do something based on a piece that was added to the Go board.
     * Implementations will be called when the board is updated - {@link GoGameSubject#notifyObserversOfPiecePlacement(GoMove)}
     * @param move the move to handle
     */
    void handlePieceAdditionEvent(GoMove move);

    /**
     * Do something based on a piece that was added to the Go board.
     * Implementations will be called when the board is updated - {@link GoGameSubject#notifyObserversOfPiecePlacement(GoMove)}
     * @param point the location of the piece that was removed
     */
    void handlePieceRemovalEvent(GoPoint point);
}
