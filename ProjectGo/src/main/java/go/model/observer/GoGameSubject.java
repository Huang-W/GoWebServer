package go.model.observer;


import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;

/**
 * A subject that notifies its observers of when interesting events happen in a
 * game of Go. A subject notifies a set of observers when a move is made or a piece is
 * captured, and notifies a different set of observers when the game ends and a winner is decided.
 */
public interface GoGameSubject {
    /**
     * Adds a move observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when move events happen in this game.
     * @param observer the observer to register, not null
     */
    void addMoveObserver(GoMoveObserver observer);

    /**
     * Adds a game observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when the game ends and will be informed who the winner is.
     * @param observer the observer to register, not null
     */
    void addGameObserver(GoGameObserver observer);
    
    /**
     * Adds a game observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when game settings change.
     * @param observer the observer to register, not null
     */
    void addModelConfigObserver(GoModelConfigObserver observer);

    /**
     * Notifies all of the move observers that a stone was added to the board.
     * @param move the move to tell the observers about
     */
    void notifyObserversOfPiecePlacement(GoMove move);

    /**
     * Notifies all of the move observers that a stone was removed from the Go board.
     * @param point the location of the stone that was removed
     */
    void notifyObserversOfPieceRemoval(GoPoint point);

    /**
     * Notifies all of the game observers that the game ended and a winner has been decided
     * @param winner which color won
     */
    void notifyObserversOfGameEnd(StoneColor winner);
    
    /**
     * Notifies all of the game observers that the board size has changed
     * @param boardSize
     */
    void notifyObserversOfBoardSizeChange(int boardSize);
}
