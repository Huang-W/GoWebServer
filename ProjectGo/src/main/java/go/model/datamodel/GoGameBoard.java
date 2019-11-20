package go.model.datamodel;

import java.util.Optional;

/**
 * Stores the state of a go board.
 */
public interface GoGameBoard {
    /**
     * Get the stone color at a particular location, or {@link Optional#empty()}
     * if that location is empty.
     * @param location the location to get the stone for
     */
    Optional<StoneColor> getStone(GoPoint location);

    /**
     * Place a stone of a color at a location.
     * @param move The stone color and point to place.
     */
    void setStone(GoMove move);

    /**
     * Remove a stone from a location.
     * @param location the location to remove the stone from.
     */
    void removeStone(GoPoint location);

    /**
     * Return the size of a side of this go board.
     * @return This go board's size.
     */
    int size();
}
