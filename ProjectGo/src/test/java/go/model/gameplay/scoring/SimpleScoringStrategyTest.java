package go.model.gameplay.scoring;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoGameBoardImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleScoringStrategyTest {
    private static final int TEST_SIZE = 3;
    private SimpleScoringStrategy goScoringStrategy;
    @Before
    public void setUp() throws Exception {
        this.goScoringStrategy = new SimpleScoringStrategy();
    }
    @Test
    public void testDetermineWinner_CountsStonesBlackWins() {
        // setup
        GoGameBoard board = new GoGameBoardImpl(TEST_SIZE);
        board.setStone(GoMove.of(0, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(1, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(2, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(0, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(1, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(2, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(0, 2, StoneColor.WHITE));
        board.setStone(GoMove.of(1, 2, StoneColor.WHITE));
        board.setStone(GoMove.of(2, 2, StoneColor.WHITE));

        // run
        StoneColor winner = goScoringStrategy.determineWinner(board);
        // verify
        assertEquals(StoneColor.BLACK, winner);
    }
    @Test
    public void testDetermineWinner_CountsStonesWhiteWins() {
        // setup
        GoGameBoard board = new GoGameBoardImpl(TEST_SIZE);
        board.setStone(GoMove.of(0, 0, StoneColor.WHITE));
        board.setStone(GoMove.of(1, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(2, 0, StoneColor.WHITE));
        board.setStone(GoMove.of(0, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(1, 1, StoneColor.WHITE));
        board.setStone(GoMove.of(2, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(0, 2, StoneColor.WHITE));
        board.setStone(GoMove.of(1, 2, StoneColor.BLACK));
        board.setStone(GoMove.of(2, 2, StoneColor.WHITE));

        // run
        StoneColor winner = goScoringStrategy.determineWinner(board);
        // verify
        assertEquals(StoneColor.WHITE, winner);
    }
    @Test
    public void testDetermineWinner_PicksWhiteInTies() {
        // setup
        GoGameBoard board = new GoGameBoardImpl(TEST_SIZE);
        board.setStone(GoMove.of(0, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(1, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(2, 0, StoneColor.BLACK));
        board.setStone(GoMove.of(0, 1, StoneColor.BLACK));
        board.setStone(GoMove.of(1, 1, StoneColor.WHITE));
        board.setStone(GoMove.of(2, 1, StoneColor.WHITE));
        board.setStone(GoMove.of(0, 2, StoneColor.WHITE));
        board.setStone(GoMove.of(1, 2, StoneColor.WHITE));

        // run
        StoneColor winner = goScoringStrategy.determineWinner(board);
        // verify
        assertEquals(StoneColor.WHITE, winner);
    }
}