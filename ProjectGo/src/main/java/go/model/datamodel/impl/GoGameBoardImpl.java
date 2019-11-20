package go.model.datamodel.impl;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoMoveObserver;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GoGameBoardImpl implements GoGameBoard, GoMoveObserver {
    private int size;
    private List<List<StoneColor>> board;

    public GoGameBoardImpl(int size) {
        this.size = size;
        board = IntStream.range(0, size)
                .boxed()
                .map(i -> IntStream.range(0, size)
                        .boxed()
                        .map(j -> (StoneColor) null)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StoneColor> getStone(GoPoint location) {
        return Optional.ofNullable(board.get(location.getX()).get(location.getY()));
    }

    @Override
    public void setStone(GoMove move) {
        GoPoint point = move.getPoint();
        board.get(point.getX()).set(point.getY(), move.getStoneColor());
    }

    @Override
    public void removeStone(GoPoint location) {
        board.get(location.getX()).set(location.getY(), null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        setStone(move);
    }

    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        removeStone(point);
    }
}
