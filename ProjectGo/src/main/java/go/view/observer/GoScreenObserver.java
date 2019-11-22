package go.view.observer;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import go.model.observer.GoGameSubject;

/**
 * An interface for objects that listen to the User's Input
 * Implementations define what to do when they are notified
 * of User Input 
 * Implementations can register themselves to be notified at {@link GoGameSubject}
 */
public interface GoScreenObserver {

	/**
	 * Handle a ButtonPress event from the user
	 * @param event The event to handle
	 */
	void handleActionEvent(ActionEvent event);
	
	/**
	 * Handle a MouseClick event from the user
	 * @param event The event to handle
	 */
	void handleMouseEvent(MouseEvent event);
}
