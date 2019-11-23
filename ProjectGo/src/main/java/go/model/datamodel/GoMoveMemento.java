package go.model.datamodel;

import java.util.Collection;

/**
 * Records what ocucurred in a single Go move to record the history of a game.
 */
public interface GoMoveMemento {
    /**
     * Get the piece(s) that were added during this move.
     * @return a collection of pieces that were added during this move.
     */
    Collection<GoMove> getAddedPieces();

    /**
     * Get the piece(s) that were removed during this move.
     * @return a collection of pieces that were removed during this move.
     */
    Collection<GoMove> getRemovedPieces();
}
