package go.model.gameplay.capturing;

import go.model.datamodel.GoGameBoard;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.gameplay.GoCapture;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoPointImpl;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

public class GoCaptureImpl implements GoCapture {
	
	private int BOARD_SIZE;
    private List<GoPoint> neighborsInChain;
    private List<GoPoint> capturedPoints;
    private GoGameBoard theBoard;
    
    public GoCaptureImpl(int size) {
    	BOARD_SIZE = size;
        neighborsInChain = new LinkedList<GoPoint>();
        capturedPoints = new LinkedList<GoPoint>();
    }

    @Override
    public List<GoPoint> capturePiecesForMove(GoGameBoard board, GoMove move) {
    	
    	this.neighborsInChain.clear();
    	this.capturedPoints.clear();
    	theBoard = board;
    	
    	GoPoint capturePoint = move.getPoint();
    	StoneColor oppositeColor = move.getStoneColor().equals(StoneColor.BLACK) ? 
    			StoneColor.WHITE : StoneColor.BLACK;
    	
    	GoPoint westPoint = GoPointImpl.of(capturePoint.getX()-1, capturePoint.getY());
    	GoPoint eastPoint = GoPointImpl.of(capturePoint.getX()+1, capturePoint.getY());
    	GoPoint northPoint = GoPointImpl.of(capturePoint.getX(), capturePoint.getY()-1);
    	GoPoint southPoint = GoPointImpl.of(capturePoint.getX(), capturePoint.getY()+1);
    	
    	// this checks westPoint: xCoord-1
    	// find same color stones and add them to the chain
    	this.findNeighborsOfSameColor(westPoint, oppositeColor);
    	// if the current chain has no liberties, add them to captured points
    	if (chainContainsStonesWithLiberties() == false)
    		neighborsInChain.forEach(goPoint->capturedPoints.add(goPoint));
    	// clear the chain to prepare for the next point adjacent to GoMove move
		neighborsInChain.clear();
		
		// check eastPoint: xCoord+1
    	this.findNeighborsOfSameColor(eastPoint, oppositeColor);
    	if (chainContainsStonesWithLiberties() == false)
    		neighborsInChain.forEach(goPoint->capturedPoints.add(goPoint));
		neighborsInChain.clear();
		
		// northPoint: yCoord-1
    	this.findNeighborsOfSameColor(northPoint, oppositeColor);
    	if (chainContainsStonesWithLiberties() == false)
    		neighborsInChain.forEach(goPoint->capturedPoints.add(goPoint));
		neighborsInChain.clear();
		
		// southPoint: yCoord+1
    	this.findNeighborsOfSameColor(southPoint, oppositeColor);
    	if (chainContainsStonesWithLiberties() == false)
    		neighborsInChain.forEach(goPoint->capturedPoints.add(goPoint));
		neighborsInChain.clear();
		
		if (capturedPoints.size() == 0) {
			checkForSuicideChain(capturePoint, move.getStoneColor());
			if (chainContainsStonesWithLiberties() == false)
				neighborsInChain.forEach(goPoint->capturedPoints.add(goPoint));
			neighborsInChain.clear();
		}
		
        return capturedPoints.size() == 0? Collections.emptyList() : capturedPoints;
    }
    
    /**
     * Check if this chain of stones is alive or dead
     * @return true if this chain is alive
     */
    private boolean chainContainsStonesWithLiberties() {
    	for (GoPoint point : neighborsInChain)
    	{
        	if (point.getX() != 0) {
        		GoPoint westPoint = GoPointImpl.of(point.getX()-1, point.getY());
        		if (!theBoard.getStone(westPoint).isPresent())
        			return true;
        	}
        	if (point.getX() != BOARD_SIZE - 1) {
	    		GoPoint eastPoint = GoPointImpl.of(point.getX()+1, point.getY());
	    		if (!theBoard.getStone(eastPoint).isPresent())
	    			return true;
        	}
        	if (point.getY() != 0) {
	    		GoPoint northPoint = GoPointImpl.of(point.getX(), point.getY()-1);
	    		if (!theBoard.getStone(northPoint).isPresent())
	    			return true;
        	}
        	if (point.getY() != BOARD_SIZE - 1) {
	    		GoPoint southPoint = GoPointImpl.of(point.getX(), point.getY()+1);
	    		if (!theBoard.getStone(southPoint).isPresent())
	    			return true;
        	}
    	}
    	return false;
    }
    
    /**
     * Find neighbors of the same color and add them to the chain
     * This is a recursive function
     * @param point The point to find neighbors of
     * @param color The color to compare against
     */
    private void findNeighborsOfSameColor(GoPoint point, StoneColor color) {
    	
    	if (point.getX() < 0 || point.getX() > BOARD_SIZE - 1)
    		return;
    	if (point.getY() < 0 || point.getY() > BOARD_SIZE - 1)
    		return;
    	if (theBoard.getStone(point).isPresent() == false)
    		return;
    	if (neighborsInChain.contains(point))
    		return;
    	
    	if (theBoard.getStone(point).get().equals(color))
    		neighborsInChain.add(point);
    	else
    		return;
    	
    	GoPoint westPoint = GoPointImpl.of(point.getX()-1, point.getY());
    	findNeighborsOfSameColor(westPoint, color);
    	
    	GoPoint eastPoint = GoPointImpl.of(point.getX()+1, point.getY());
    	findNeighborsOfSameColor(eastPoint, color);
    	
    	GoPoint northPoint = GoPointImpl.of(point.getX(), point.getY()-1);
    	findNeighborsOfSameColor(northPoint, color);
    	
    	GoPoint southPoint = GoPointImpl.of(point.getX(), point.getY()+1);
    	findNeighborsOfSameColor(southPoint, color);
    }
    
    /**
     * 
     * @param point
     * @param color
     */
    private void checkForSuicideChain(GoPoint capturePoint, StoneColor color) {
    	
    	neighborsInChain.add(capturePoint);
    	
    	GoPoint westPoint = GoPointImpl.of(capturePoint.getX()-1, capturePoint.getY());
    	findNeighborsOfSameColor(westPoint, color);
    	
    	GoPoint eastPoint = GoPointImpl.of(capturePoint.getX()+1, capturePoint.getY());
    	findNeighborsOfSameColor(eastPoint, color);
    	
    	GoPoint northPoint = GoPointImpl.of(capturePoint.getX(), capturePoint.getY()-1);
    	findNeighborsOfSameColor(northPoint, color);
    	
    	GoPoint southPoint = GoPointImpl.of(capturePoint.getX(), capturePoint.getY()+1);
    	findNeighborsOfSameColor(southPoint, color);
    }
}