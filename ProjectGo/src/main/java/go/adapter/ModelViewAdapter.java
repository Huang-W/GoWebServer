package go.adapter;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Point;
import go.controller.GoMoveController;
import go.controller.GoViewController;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.*;
import go.view.datamodel.impl.GoViewImpl;
import go.view.observer.GoViewObserver;
import go.view.observer.GoViewSubject;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;
import go.view.datamodel.impl.GoViewImpl;

public class ModelViewAdapter implements GoGameObserver, GoMoveObserver {

    public ModelViewAdapter(GoViewController goViewController) {
        this.goViewController = goViewController;
    }

    private static final int BOARD_SIZE = 9;
    public static final int NUM_TILES = BOARD_SIZE - 1;
    public static final int TILE_SIZE = GoViewImpl.CENTER_DIM.width / (NUM_TILES + 2);
    public static final int BORDER_SIZE = TILE_SIZE;

    private GoViewController goViewController;

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        int x = move.getPoint().getX() * TILE_SIZE + BORDER_SIZE;
        int y = move.getPoint().getY() * TILE_SIZE + BORDER_SIZE;
        Color color = move.getStoneColor().equals(StoneColor.BLACK) ? Color.BLACK : Color.WHITE;
        goViewController.drawStone(x, y, color);
        System.out.println("col: " + x + " row: " + y);
    }

    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        int x = point.getX();
        int y = point.getY();
        goViewController.drawEmptySpace(x, y);
    }

    @Override
    public void handleGameEnd(StoneColor winner) {
        // TODO Auto-generated method stub
        if(winner.equals(winner.BLACK))
        {
            System.out.println("Black is the winner!");
        }
        else
        {
            System.out.println("White is the winner!");
        }

    }

}


