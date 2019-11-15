package go.controller.impl;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

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
	public void setStone(int x, int y, Color color) {
		view.setStone(new Point(x, y), color);
	}
	
	@Override
	public void removeStone(int x, int y) {
		view.removeStone(new Point(x, y));
	}
	
	@Override
	public GoViewSubject getViewSubject() {
		return subject;
	}

}
