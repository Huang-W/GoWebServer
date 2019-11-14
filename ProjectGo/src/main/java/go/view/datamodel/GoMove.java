package go.view.datamodel;

import java.awt.Color;
import java.awt.Point;

/**
 * A move in a game of Go.
 * Each move is the placement of one stone at a particular location.
 */
public interface GoMove {
    /**
     * Get the location the stone was placed in this move.
     * @return a location of the move
     */
    Point getPoint();

    /**
     * Get the color of the stone thatw as placed in this move.
     * @return the color of this move
     */
    Color getStoneColor();
}
