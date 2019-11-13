package go.model.gameplay;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;

import java.util.List;

/**
 * Capture pieces for a go board state with a new piece added to the board.
 */
public interface GoCapture {
    /**
     * Determine which pieces are captured based on an existing board state
     * and a new move.
     * @param board The board state before the move was made
     * @param move The new move that was just made
     * @return A list of locations of pieces that were removed from the game based on this move.
     */
    List<GoPoint> capturePiecesForMove(GoGameBoard board, GoMove move);
}
