package go.model.datamodel;

/**
 * A move in a game of Go.
 * Each move is the placement of one stone at a particular location.
 */
public interface GoMove {
    /**
     * Get the location the stone was placed in this move.
     * @return a location of the move
     */
    GoPoint getPoint();

    /**
     * Get the color of the stone thatw as placed in this move.
     * @return the color of this move
     */
    StoneColor getStoneColor();
}
