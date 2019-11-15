package go.view.observer;

import java.awt.Point;

/**
 * A subject that notifies its observers of when interesting events happen in the screen of
 * a game of Go. A subject notifies a set of observers when the screen is clicked or when
 * a button is clicked.
 */
public interface GoViewSubject {
	
    /**
     * Adds a screen observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when a mouse
     * is clicked or a button is pressed.
     * @param observer the observer to register, not null
     */
    void addViewObserver(GoViewObserver observer);
    
    /**
     * Notifies all view observers that a point was clicked on the board.
     * @param point the location in pixels that a point was clicked
     */
    void notifyObserversOfMouseClick(Point point);
    
    /**
     * Notifies all view observers that a PASS was requested in the GameScreen UI.
     */
    void notifyObserversOfPassTurnRequest();
    
    /**
     * Notifies all view observers that an UNDO was requested in the GameScreen UI.
     */
    void notifyObserversOfUndoMoveRequest();
    
    /**
     * Notifies all view observers when the USER closes the GameScreen window
     */
    void notifyObserversOfWindowClose();
}
