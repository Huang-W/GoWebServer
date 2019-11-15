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
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class GoCaptureImpl implements GoCapture {
    int SIZE;
    public GoCaptureImpl(int size) {
        SIZE = size;
    }
    @Override
    public List<GoPoint> capturePiecesForMove(GoGameBoard board, GoMove move) {
        // @TODO: implement me!
        List<GoPoint> capturedPoints = new ArrayList<GoPoint>();  
        int row = move.getPoint().getX();
        int col = move.getPoint().getY();
        // get neighbors
        List<GoMoveImpl> neighbors = getNeighbours(row, col, board);
        for (GoMoveImpl neighbor : neighbors) {
            if (neighbor.getStoneColor() != move.getStoneColor()) {
                //todo  
                chainLiberties(neighbor, board, capturedPoints);                              
            }
        }
        return capturedPoints.size() == 0? Collections.emptyList() : capturedPoints;
    }

private void chainLiberties(GoMoveImpl stone, GoGameBoard board, List<GoPoint> capturedPoints) {
    boolean chainHasNoliberties = true;
    //when chain has no liberties, return true
    Set<GoMoveImpl> chain = findChain( stone, board);
    for (GoMoveImpl e : chain) {
        if(getNeighbours(e.getPoint().getX(), e.getPoint().getY(), board).size() != 4)
        chainHasNoliberties = false;
    }
    if(chainHasNoliberties){
        for (GoMoveImpl e : chain) {
            capturedPoints.add(e.getPoint());
        }
    }
}

private Set<GoMoveImpl> findChain(GoMoveImpl stone, GoGameBoard board) {
    int row = stone.getPoint().getX();
    int col = stone.getPoint().getY();
    StoneColor color = stone.getStoneColor();
    Set<GoMoveImpl> elements = new HashSet<GoMoveImpl>(); 
    Set<GoMoveImpl> currentElements = new HashSet<GoMoveImpl>(); 
    currentElements.add(stone);
    Set<GoMoveImpl> prevElements;
    boolean allPresent = false;
    while (!allPresent) {
        allPresent = false;
        boolean flag = true;
        for (GoMoveImpl e : currentElements) {
            if(!elements.contains(e))
                flag = false;
        }
        if(flag)
            allPresent = true;
        for (GoMoveImpl e : currentElements) {
           elements.add(e);
        }
        prevElements = currentElements;
        currentElements = new HashSet<GoMoveImpl>(); 
        for (GoMoveImpl e : prevElements) {
            List<GoMoveImpl> neighbors = getSameColorNeighbours(e.getPoint().getX(), e.getPoint().getY(), board, e.getStoneColor());
            for (GoMoveImpl a : neighbors) {
                currentElements.add(a);
            }
         }        
    }
    return elements;
}

private  List<GoMoveImpl> getNeighbours(int row, int col, GoGameBoard board) {
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
    return neighbors;
}

private  List<GoMoveImpl> getSameColorNeighbours(int row, int col, GoGameBoard board, StoneColor color) {
    List<GoMoveImpl> neighbors = getNeighbours(row, col, board);
    for (GoMoveImpl neighbor : neighbors) {
        if (neighbor.getStoneColor() != color) {
            neighbors.remove(neighbor);          
        }
    }
    return neighbors;
}
}
