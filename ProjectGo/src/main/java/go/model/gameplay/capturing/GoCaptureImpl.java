package go.model.gameplay.capturing;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.gameplay.GoCapture;
import go.model.datamodel.impl.GoMoveImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.model.datamodel.StoneColor;
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
        System.out.println("neighbors "+neighbors);
        for (GoMoveImpl neighbor : neighbors) {
            System.out.println("neighbor "+neighbor);
            if (neighbor.getStoneColor() != move.getStoneColor()) {
                //todo  
                System.out.println("neighbor getStoneColor diff "+neighbor.getStoneColor());
                chainLiberties(neighbor, board, capturedPoints);                              
            }
        }
        return capturedPoints.size() == 0? Collections.emptyList() : capturedPoints;
    }

private void chainLiberties(GoMoveImpl stone, GoGameBoard board, List<GoPoint> capturedPoints) {
    
    //when chain has no liberties, return true
    Set<GoMoveImpl> chain = findChain( stone, board);
    Set<Boolean> chainElemntHasLibertieSet = new HashSet<Boolean>();  
    System.out.println("chain "+chain);
    for (GoMoveImpl e : chain) {
        boolean chainHasNoliberties = false;
        System.out.println("e in chain "+e);
        int x = e.getPoint().getX();
        int y = e.getPoint().getY();
        int size = getNeighbours(x, y, board).size();

        if(x == 0 || y == 0 || x == SIZE-1 || y == SIZE-1){
            if(x==0 && y ==0 && size ==2)
                chainHasNoliberties = true;
            else if ((x==0 || y==0) && size ==3)
                chainHasNoliberties = true;
            if(x==SIZE-1 && y ==SIZE-1 && size ==2)
                chainHasNoliberties = true;
            else if ((x==SIZE-1 || y==SIZE-1) && size ==3)
                chainHasNoliberties = true;
        }
        else{
            if(size == 4)
            chainHasNoliberties = true;
        }      
        chainElemntHasLibertieSet.add(chainHasNoliberties);
    }
    boolean allTrue = true;
    for (boolean e : chainElemntHasLibertieSet) {
        if(!e)
            allTrue = false;
    }
    System.out.println("allTrue "+allTrue);
    if(allTrue){
        System.out.println("allTrue "+allTrue);
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
    if (col > 0) {
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
    System.out.println("getNeighbours neighbors "+neighbors);
    return neighbors;
}

private  List<GoMoveImpl> getSameColorNeighbours(int row, int col, GoGameBoard board, StoneColor color) {
    List<GoMoveImpl> neighbors = getNeighbours(row, col, board);
    List<GoMoveImpl> sameColorNeighbors = new ArrayList<>();
    for (GoMoveImpl neighbor : neighbors) {
        if (neighbor.getStoneColor() == color) {
            sameColorNeighbors.add(neighbor);
        }
    }
    return sameColorNeighbors;
}
}
