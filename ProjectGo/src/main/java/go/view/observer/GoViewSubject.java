package go.view.observer;

import java.awt.Point;

import go.view.datamodel.GoAction;

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
     * @param point the location in pixels that a point was click
     */
    void notifyObserversOfMouseClick(Point point);
    
    /**
     * Notifies all view observers that a button was pressed in the UI.
     * @param action the action that the button triggered
     */
    void notifyObserversOfButtonPress(GoAction action);
}
