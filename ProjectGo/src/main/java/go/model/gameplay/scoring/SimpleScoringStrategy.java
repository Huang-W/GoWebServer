package go.model.gameplay.scoring;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoPointImpl;
import go.model.gameplay.GoScoringStrategy;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;



/**
 * Scores Go boards in a simple way: just counting stones.
 */
public class SimpleScoringStrategy implements GoScoringStrategy {
    @Override
    public StoneColor determineWinner(GoGameBoard endingBoard) {
        return countStonesOfColor(endingBoard, StoneColor.BLACK) >
                countStonesOfColor(endingBoard, StoneColor.WHITE) ?
                StoneColor.BLACK : StoneColor.WHITE;
    }
    private long countStonesOfColor(GoGameBoard board, StoneColor color) {
        return IntStream.range(0, board.size())
                .mapToObj(i -> IntStream.range(0, board.size())
                        .mapToObj(j -> board.getStone(GoPointImpl.of(i, j))))
                .flatMap(Function.identity())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(stoneColor -> stoneColor == color)
                .count();
    }
}
