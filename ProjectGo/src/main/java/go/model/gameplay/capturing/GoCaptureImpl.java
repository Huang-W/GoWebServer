package go.model.gameplay.capturing;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.gameplay.GoCapture;
import go.model.datamodel.impl.GoPointImpl;
import go.model.datamodel.impl.GoMoveImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class GoCaptureImpl implements GoCapture {
    int SIZE;
    public GoCaptureImpl(int size) {
        SIZE = size;
    }
    @Override
    public List<GoPoint> capturePiecesForMove(GoGameBoard board, GoMove move) {
        // @TODO: implement me!

        int row = move.getPoint().getX();
        int col = move.getPoint().getY();
        // get neighbors
        List<GoMoveImpl> neighbors = new ArrayList<GoMoveImpl>();        
        if (row > 0) {
            GoPoint location = new GoPointImpl(row - 1, col);
            Optional<StoneColor> stone = board.getStone(location);
            if(stone.isPresent()){
                neighbors.add(new GoMoveImpl(location, stone.get()));    
            }                    
        }
        if (row < SIZE - 1) {
            GoPoint location = new GoPointImpl(row + 1, col);
            Optional<StoneColor> stone = board.getStone(location);
            if(stone.isPresent()){
                neighbors.add(new GoMoveImpl(location, stone.get()));    
            } 
        }
        if (col > 1) {
            GoPoint location = new GoPointImpl(row, col - 1);
            Optional<StoneColor> stone = board.getStone(location);
            if(stone.isPresent()){
                neighbors.add(new GoMoveImpl(location, stone.get()));    
            }             
        }
        if (col < SIZE - 1) {
            GoPoint location = new GoPointImpl(row, col + 1);
            Optional<StoneColor> stone = board.getStone(location);
            if(stone.isPresent()){
                neighbors.add(new GoMoveImpl(location, stone.get()));    
            }             
        }
        for (GoMoveImpl neighbor : neighbors) {

        }

        return Collections.emptyList();
    }
}
