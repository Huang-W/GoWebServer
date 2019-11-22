package go.view.observer;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 * A subject that notifies its observers of when interesting events happen in the screen of
 * a game of Go. A subject notifies a set of observers when the screen is clicked or when
 * a button is clicked.
 */
public interface GoScreenSubject {
	
	/**
     * Adds a screen observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when a mouse
     * is clicked or a button is pressed.
     * @param observer the observer to register, not null
     */
	void registerGoScreenObserver(GoScreenObserver observer);
	
	/**
     * Notifies all screen observers that an action event occurred.
     * @param event the kind of event that occurred.
     */
	void notifyObserversOfActionEvent(ActionEvent event);
	
	/**
     * Notifies all screen observers that a mouse event occurred.
     * @param event the kind of event that occurred.
     */
	void notifyObserversOfMouseEvent(MouseEvent event);
}
