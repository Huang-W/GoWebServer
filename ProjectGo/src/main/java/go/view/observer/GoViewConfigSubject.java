package go.view.observer;

/**
 * A subject that notifies its observers when the GoGame is being configured
 * A subject that notifies its observers when the BoardSize has changed
 */
public interface GoViewConfigSubject {
	
    /**
     * Adds a screen observer implementation to this subject's list of observers.
     * Observers that register themselves with this method will be notified when a mouse
     * is clicked or a button is pressed.
     * @param observer the observer to register, not null
     */
    void addViewConfigObserver(GoViewConfigObserver observer);
    
    /**
     * Notifies all view observers when the USER configures a new BoardSize
     * @param boardSize The BoardSize to set
     */
    void notifyObserversOfScreenSizeConfigEvent(int boardSize);
}
