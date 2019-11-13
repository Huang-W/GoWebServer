package go.model.datamodel;

/**
 * A point on a Go board.
 */
public interface GoPoint {
    /**
     * Get the x position of this point on the go board. 0-9.
     * @return the x position of this point
     */
    int getX();

    /**
     * Get the y position of this point on the go board. 0-9.
     * @return the y position of this point
     */
    int getY();
}
