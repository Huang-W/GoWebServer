package go.model.gameplay.scoring;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.StoneColor;
import go.model.gameplay.GoScoringStrategy;

/**
 * Score Go boards in the Japanese style.
 * @todo determine which strategy we'll actually use - we needn't implement both.
 */
public class JapaneseScoringStrategy implements GoScoringStrategy {
    @Override
    public StoneColor determineWinner(GoGameBoard endingBoard) {
        // @TODO: implement me!
        return Math.random() < .5 ? StoneColor.BLACK : StoneColor.WHITE;
    }
}
