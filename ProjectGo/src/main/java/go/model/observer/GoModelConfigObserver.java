package go.model.observer;

/**
 * An interface for objects that listen to changes in the Go Game configuration
 * Implementations define what to do when they are notified
 * of a change in BoardSize, ScoringStrategy, play handicap, or KOMI
 * Implementations can register themselves to be notified at {@link GoGameSubject}
 */
public interface GoModelConfigObserver {
	
	/**
	 * Notifies observers when the default board size has changed
	 * @param boardSize the size of the new board
	 */
	void handleBoardSizeChange(int boardSize);
}
