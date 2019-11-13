package go.model.gameplay;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.StoneColor;

/**
 * An interface for algorithms for scoring a game board in Go.
 */
public interface GoScoringStrategy {
    /**
     * Given a board ending state determine the winner.
     * @param endingBoard The board state at the end of the game
     * @return Which stone color won.
     */
    StoneColor determineWinner(GoGameBoard endingBoard);
}
