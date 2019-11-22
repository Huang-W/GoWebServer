package go.view.screen.impl;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import go.view.observer.GoScreenObserver;
import go.view.observer.GoScreenSubject;
import go.view.screen.GoScreen;

@SuppressWarnings("serial")
public abstract class GoScreenImpl extends JComponent implements GoScreen, GoScreenSubject {

	private List<GoScreenObserver> observers;
	
	public GoScreenImpl() {
		observers = new LinkedList<>();
	}
	
	@Override
	public void notifyObserversOfActionEvent(ActionEvent event) {
		observers.forEach(observer -> observer.handleActionEvent(event));
	}

	@Override
	public void notifyObserversOfMouseEvent(MouseEvent event) {
		observers.forEach(observer -> observer.handleMouseEvent(event));
	}

	@Override
	public void registerGoScreenObserver(GoScreenObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void showScreen() {
		this.setVisible(true);
	}

	@Override
	public void hideScreen() {
		this.setVisible(false);
	}

}
