package go.controller.impl;

import java.awt.Color;
import java.awt.Point;

import go.controller.GoViewController;
import go.view.datamodel.GoMove;
import go.view.datamodel.GoView;
import go.view.datamodel.impl.GoMoveImpl;
import go.view.datamodel.impl.GoViewImpl;
import go.view.observer.GoViewSubject;

public class GoViewControllerImpl implements GoViewController {

	private GoView view;
	private GoViewSubject subject;
	
    public GoViewControllerImpl() {
        this(new GoViewImpl());
    }
    public GoViewControllerImpl(GoViewImpl goView) {
        this.view = goView;
        this.subject = goView;
    }

	@Override
	public void drawStone(int x, int y, Color color) {
		Point point = new Point(x, y);
		GoMove goMove = new GoMoveImpl(point, color);
		view.drawStone(goMove);
	}
	
	@Override
	public void drawEmptySpace(int x, int y) {
		view.drawEmptySpace(new Point(x, y));
	}
	
	@Override
	public GoViewSubject getViewSubject() {
		return subject;
	}
	
	@Override
	public void announceGameWinner(Color color) {
		// TODO Auto-generated method stub
		
	}

}
