package go.view.datamodel;

import java.awt.Color;
import java.awt.Point;

/**
 * What you see in a game of Go
 * Records the board state and handles moves.
 */
public interface GoAppView {

    /**
     * Place a stone of a color at a location.
     * @param location the Point to place.
     * @param color the Color of the stone
     */
    void setStone(Point location, Color color);

    /**
     * Remove a stone from a location.
     * @param location the location to remove the stone from.
     */
    void removeStone(Point location);
}
