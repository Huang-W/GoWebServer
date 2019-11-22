package go.view.observer;

/**
 * An interface for objects that listen to when the Go game changes configuration.
 * Implementations define what to do when they are notified
 * of a change in BoardSize, KOMI, or ScoringStrategy
 * Implementations can register themselves to be notified at {@link GoGameSubject}
 */
public interface GoViewConfigObserver {

	/**
	 * Do something when the user configures the size of the board
	 * @param boardSize The new boardSize to configure
	 */
	void handleBoardSizeConfigure(int boardSize);
	
}
