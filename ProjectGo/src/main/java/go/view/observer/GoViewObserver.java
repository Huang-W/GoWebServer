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
     * Do something based on a point that was clicked on the board.
     * Implementations will be called when the board is updated - {@link GoViewSubject#handleMouseClieckEvent(Point)}
     * @param point the point to handle
     */
    void handleMouseClickEvent(Point point);
    
    /**
     * Do something based on a button that was pressed in the UI
     * Implementations will be called when the board is updated - {@link GoViewSubject#handleButtonPressEvent(ActionEvent)}
     * @param even the Event to handle
     */
    void handleButtonPressEvent(ActionEvent event);
    
}
