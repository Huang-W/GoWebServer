package go.view.datamodel;

import java.awt.Color;
import java.awt.Point;

/**
 * What you see in a game of Go
 * Records the board state and handles moves.
 */
public interface GoView {

    /**
     * Draw a colored stone at a location.
     * @param move the Move to draw
     */
    void drawStone(GoMove move);

    /**
     * Remove a stone from a location by drawing an empty space.
     * @param location the location to remove the stone from.
     */
    void drawEmptySpace(Point location);
    
    /**
     * Announce the Game Winner!
     * @param color the Color of the winner
     * @param points the amount of points the winner won by
     */
    void announceGameWinner(Color color, double points);
}
