package go.controller.impl;

import go.controller.GoMoveController;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoMoveImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.model.gameplay.GoCapture;
import go.model.gameplay.GoScoringStrategy;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Arrays;

public class GoMoveControllerImplTest {
    private GoMoveController goMoveController;

    @Mock
    private GoCapture capture;

    @Mock
    private GoScoringStrategy scoringStrategy;

    @Mock
    private GoMoveObserver moveObserver;

    @Mock
    private GoGameObserver gameObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        goMoveController = new GoMoveControllerImpl(capture, scoringStrategy);
        goMoveController.getGameSubject().addMoveObserver(moveObserver);
        goMoveController.getGameSubject().addGameObserver(gameObserver);

    }

    @Test
    public void testMakeNextPlayersMove_NotifiesObserverOfMoves() {

        // run
        goMoveController.makeNextPlayersMove(0, 0);
        goMoveController.makeNextPlayersMove(1, 1);
        goMoveController.makeNextPlayersMove(2, 2);

        // verify
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(0, 0), StoneColor.BLACK));
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(1, 1), StoneColor.WHITE));
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(2, 2), StoneColor.BLACK));
    }

    @Test
    public void testMakeNextPlayersMove_ReportsCapturedPieces() {
        // setup
        when(capture.capturePiecesForMove(any(), eq(new GoMoveImpl(GoPointImpl.of(2, 1), StoneColor.WHITE))))
                .thenReturn(Arrays.asList(GoPointImpl.of(3, 4)));

        // run
        goMoveController.makeNextPlayersMove(0, 0);
        goMoveController.makeNextPlayersMove(1, 1);
        goMoveController.makeNextPlayersMove(3, 4);
        goMoveController.makeNextPlayersMove(2, 1);

        // verify
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(0, 0), StoneColor.BLACK));
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(1, 1), StoneColor.WHITE));
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(3, 4), StoneColor.BLACK));
        verify(moveObserver).handlePieceAdditionEvent(new GoMoveImpl(GoPointImpl.of(2, 1), StoneColor.WHITE));
        verify(moveObserver).handlePieceRemovalEvent(GoPointImpl.of(3,4));
    }
    @Test
    public void testPass_ReportsGameWinnerForSubsequentPasses() {
        // setup
        final StoneColor WINNER = StoneColor.BLACK;
        when(scoringStrategy.determineWinner(any())).thenReturn(WINNER);

        // run
        goMoveController.pass();
        goMoveController.pass();

        // verify
        verify(gameObserver).handleGameEnd(WINNER);
    }

}