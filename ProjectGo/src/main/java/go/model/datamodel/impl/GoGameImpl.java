package go.model.datamodel.impl;

import go.model.datamodel.GoGame;
import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoMoveMemento;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.gameplay.GoCapture;
import go.model.gameplay.GoScoringStrategy;
import go.model.gameplay.capturing.GoCaptureImpl;
import go.model.gameplay.scoring.SimpleScoringStrategy;
import go.model.observer.GoModelConfigObserver;
import go.model.observer.GoGameObserver;
import go.model.observer.GoGameSubject;
import go.model.observer.GoMoveObserver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


public class GoGameImpl implements GoGameSubject, GoGame {
    private GoGameBoard board;
    private StoneColor nextPlayer;
    private boolean lastMovePassed;
    private List<GoMoveObserver> moveObservers;
    private List<GoGameObserver> gameObservers;
    private List<GoModelConfigObserver> configObservers;
    private Stack<GoMoveMemento> moveHistory;
    private GoCapture capture;
    private GoScoringStrategy scoringStrategy;

    private static int BOARD_SIZE = 9;


    public GoGameImpl() {
        this(new GoCaptureImpl(), new SimpleScoringStrategy());
    }
    public GoGameImpl(GoCapture capture, GoScoringStrategy strategy) {
        nextPlayer = StoneColor.BLACK;
        lastMovePassed = false;
        moveObservers = new LinkedList<>();
        gameObservers = new LinkedList<>();
        configObservers = new LinkedList<>();
        GoGameBoardImpl board = new GoGameBoardImpl(BOARD_SIZE);
        this.board = board;
        addMoveObserver(board);
        addModelConfigObserver(board);
        this.capture = capture;
        this.scoringStrategy = strategy;
        this.moveHistory = new Stack<>();
    }

    @Override
    public void makeMove(GoPoint point) {
    	System.out.println("XY in GameGameImpl: " + point.getX() + " " + point.getY());
    	System.out.println("Size of Board: " + board.size());
    	// Point is already occupied
    	if (board.getStone(point).isPresent())
    		return;
        GoMove move = new GoMoveImpl(point, nextPlayer);
        this.notifyObserversOfPiecePlacement(move);
        List<GoPoint> potentiallyCapturedPieces = capture.capturePiecesForMove(board, move);
        System.out.println(potentiallyCapturedPieces.toString());
        if (potentiallyCapturedPieces.contains(point)) {
        	//the last move was an illegal suicide, this turn is invalid
        	this.notifyObserversOfPieceRemoval(point);
        	return;
        }
        else {
            recordMove(move, potentiallyCapturedPieces);
        	potentiallyCapturedPieces.forEach(this::notifyObserversOfPieceRemoval);
        }
        rotateNextPlayer();
        lastMovePassed = false;
    }

    private void recordMove(GoMove moveMade, List<GoPoint> capturedPieceLocations) {
        List<GoMove> removedPieceMoves = capturedPieceLocations.stream()
                .map(point -> new GoMoveImpl(point, board.getStone(point).get()))
                .collect(Collectors.toList());
        moveHistory.push(new GoMoveMementoImpl(Collections.singletonList(moveMade), removedPieceMoves));
    }

    @Override
    public void undo() {
        if (moveHistory.isEmpty()) {
            return;
        }
        GoMoveMemento lastMove = moveHistory.pop();
        lastMove.getRemovedPieces().forEach(this::notifyObserversOfPiecePlacement);
        lastMove.getAddedPieces().stream()
                .map(GoMove::getPoint)
                .forEach(this::notifyObserversOfPieceRemoval);
        rotateNextPlayer();
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
    public void reset(){
        //reset the board
        board.reset();
        //reset next player
        nextPlayer = StoneColor.BLACK;
    }
    
	@Override
	public void configureBoardSize(int size) {
		BOARD_SIZE = size;
		this.notifyObserversOfBoardSizeChange(size);
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
	public void addModelConfigObserver(GoModelConfigObserver observer) {
		configObservers.add(observer);
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
    
	@Override
	public void notifyObserversOfBoardSizeChange(int boardSize) {
		configObservers.forEach(observer -> observer.handleBoardSizeChange(boardSize));
	}

    private void rotateNextPlayer() {
        nextPlayer = nextPlayer == StoneColor.BLACK ? StoneColor.WHITE : StoneColor.BLACK;
    }
}
