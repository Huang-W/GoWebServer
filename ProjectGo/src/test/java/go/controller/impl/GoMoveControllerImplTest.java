package go.controller.impl;

import go.controller.GoMoveController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.observer.GoMoveObserver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoMoveControllerImplTest {
    private GoMoveController goMoveController;

    @Before
    public void setUp() {
        goMoveController = new GoMoveControllerImpl();
    }


    @Test
    public void testMakeNextPlayersMove_NotifiesObserverOfMoves() {

    }
}