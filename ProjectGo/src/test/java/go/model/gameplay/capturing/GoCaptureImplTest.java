package go.model.gameplay.capturing;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoGameBoardImpl;
import go.model.datamodel.impl.GoMoveImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.model.gameplay.GoCapture;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GoCaptureImplTest {
    private static final int TEST_SIZE = 5;
    private GoCapture goCapture;

    @Before
    public void setUp() throws Exception {
        goCapture = new GoCaptureImpl(TEST_SIZE);
    }

    private GoMove getMove(int x, int y, StoneColor color) {
        return new GoMoveImpl(GoPointImpl.of(x, y), color);
    }

    @Test
    public void testCapturePiecesForMove_CapturesSimpleSurroundedStone() {
        // setup
        GoGameBoard goGameBoard = new GoGameBoardImpl(TEST_SIZE);
        // set up the board so white is almost surrounded
        goGameBoard.setStone(getMove(1, 1, StoneColor.WHITE));
        goGameBoard.setStone(getMove(0, 1, StoneColor.BLACK));
        goGameBoard.setStone(getMove(1, 0, StoneColor.BLACK));
        goGameBoard.setStone(getMove(2, 1, StoneColor.BLACK));
        GoMove capturingMove = getMove(1, 2, StoneColor.BLACK);

        // run
        List<GoPoint> capturedStoneLocations = goCapture.capturePiecesForMove(goGameBoard, capturingMove);

        // verify
        // one piece was captured
        assertEquals(1, capturedStoneLocations.size());
        // it was the white piece
        assertEquals(GoPointImpl.of(1, 1), capturedStoneLocations.get(0));
    }
    @Test
    public void testCapturePiecesForMove_AdjacentStonesProtectNeighbors() {

    }
    @Test
    public void testCapturePiecesForMove_CapturesEdgeAndCornerStones() {

    }

    @Test
    public void testCapturePiecesForMove_CapturesMultipleStoneGroup() {

    }



}