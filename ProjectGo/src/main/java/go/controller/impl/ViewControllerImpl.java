package go.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import go.controller.ViewController;
import go.view.GoView;

public class ViewControllerImpl implements ViewController {

	private JFrame displayFrame;
	// I think we put the adapter here?
	// private GoSwingAdapter adapter;
	
	public ViewControllerImpl() {
		displayFrame = new GoView();
        displayFrame.addMouseListener(new MouseAdapter() {
        	@Override
			public void mousePressed(MouseEvent e) {
        		//make sure click is within boardPanel
				displayFrame.getContentPane().dispatchEvent(e);
				fireMouseEvent(e.getX(), e.getY());
			}
        });
	}
	
	@Override
	public void fireMouseEvent(int xCoord, int yCoord) {
		// adapter.fireMouseEvent(x, y);
	}

	@Override
	public void updateGameState(String gameState) {
		// displayFrame.doSomething();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// see CommandPanel "SetActionCommand()" line 29
		// adapter.actionPerformed(e.getActionCommand());
	}

}
