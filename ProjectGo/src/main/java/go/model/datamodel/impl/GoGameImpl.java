package go.model.datamodel.impl;

import go.model.datamodel.GoGame;
import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.gameplay.GoCapture;
import go.model.gameplay.GoScoringStrategy;
import go.model.gameplay.capturing.GoCaptureImpl;
import go.model.gameplay.scoring.ChineseScoringStrategy;
import go.model.gameplay.scoring.SimpleScoringStrategy;
import go.model.observer.GoGameObserver;
import go.model.observer.GoGameSubject;
import go.model.observer.GoMoveObserver;

import java.util.LinkedList;
import java.util.List;


public class GoGameImpl implements GoGameSubject, GoGame {
    private GoGameBoard board;
    private StoneColor nextPlayer;
    private boolean lastMovePassed;
    private List<GoMoveObserver> moveObservers;
    private List<GoGameObserver> gameObservers;
    private GoCapture capture;
    private GoScoringStrategy scoringStrategy;

    private static final int BOARD_SIZE = 9;


    public GoGameImpl() {
        // @todo determine which strategy we'll actually use - we needn't implement both.
        this(new GoCaptureImpl(BOARD_SIZE), new SimpleScoringStrategy());
    }

    public GoGameImpl(GoCapture capture, GoScoringStrategy strategy) {
        nextPlayer = StoneColor.BLACK;
        lastMovePassed = false;
        moveObservers = new LinkedList<>();
        gameObservers = new LinkedList<>();
        GoGameBoardImpl board = new GoGameBoardImpl(BOARD_SIZE);
        this.board = board;
        addMoveObserver(board);
        this.capture = capture;
        this.scoringStrategy = strategy;
    }

    @Override
    public void makeMove(GoPoint point) {
    	if (board.getStone(point).isPresent())
    		return;
        GoMove move = new GoMoveImpl(point, nextPlayer);
        this.notifyObserversOfPiecePlacement(move);
        capture.capturePiecesForMove(board, move)
                .forEach(this::notifyObserversOfPieceRemoval);
        rotateNextPlayer();
        lastMovePassed = false;
    }

    @Override
    public void pass() {
        if (lastMovePassed) {
            StoneColor winner = scoringStrategy.determineWinner(board);
            notifyObserversOfGameEnd(winner);
        } else {
            rotateNextPlayer();
            lastMovePassed = true;
        }
    }

    @Override
    public void addMoveObserver(GoMoveObserver observer) {
        moveObservers.add(observer);
    }

    @Override
    public void addGameObserver(GoGameObserver observer) {
        gameObservers.add(observer);
    }

    @Override
    public void notifyObserversOfPiecePlacement(GoMove move) {
        moveObservers.forEach(observer -> observer.handlePieceAdditionEvent(move));
    }

    @Override
    public void notifyObserversOfPieceRemoval(GoPoint point) {
        moveObservers.forEach(observer -> observer.handlePieceRemovalEvent(point));
    }

    @Override
    public void notifyObserversOfGameEnd(StoneColor winner) {
        gameObservers.forEach(observer -> observer.handleGameEnd(winner));
    }

    private void rotateNextPlayer() {
        nextPlayer = nextPlayer == StoneColor.BLACK ? StoneColor.WHITE : StoneColor.BLACK;
    }
}
