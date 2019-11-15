package go.view.observer;

import java.awt.Point;
import java.awt.event.ActionEvent;

/**
 * An interface for objects that listen to Go UI events.
 * Implementations define what to do when they are notified of
 * Points clicked and Action events triggered.
 * Implementations can register themselves to be notified at {@link GoViewSubject}
 */
public interface GoViewObserver {
	
	/**
	 * Do something when the user clicks the board
	 * @param point the Point the user clicked
	 */
	void handleMouseClickEvent(Point point);
	
	/**
	 * Do something when the user passes a turn
	 */
	void handlePassTurnRequest();
	
	/**
	 * Do something when the user requests an undo
	 */
	void handleUndoMoveRequest();
    
	/**
	 * Do something when the user closes the GameScreen window
	 */
	void handleWindowClose();
}
