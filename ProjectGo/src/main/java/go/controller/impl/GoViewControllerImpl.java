package go.controller.impl;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import go.controller.GoViewController;
import go.view.datamodel.GoMove;
import go.view.datamodel.GoAppView;
import go.view.datamodel.impl.GoMoveImpl;
import go.view.datamodel.impl.GoAppViewImpl;
import go.view.observer.GoViewSubject;

public class GoViewControllerImpl implements GoViewController {

	private GoAppView view;
	private GoViewSubject subject;
	
	//private GoAppViewImpl goView1;
	
	// I think we put the adapter here?
	// private Adapter adapter;
	
    public GoViewControllerImpl() {
        this(new GoAppViewImpl());
    }
    public GoViewControllerImpl(GoAppViewImpl goView) {
        this.view = goView;
        this.subject = goView;
		//goView1 = new GoAppViewImpl();
        goView.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseClicked(MouseEvent e) {
				goView.getContentPane().dispatchEvent(e);
        		System.err.println("Event from... ");
			}
        });
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
