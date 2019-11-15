package go.view.observer;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public interface GoScreenSubject {
	
	void registerGoScreenObserver(GoScreenObserver observer);
	
	void notifyObserversOfActionEvent(ActionEvent event);
	
	void notifyObserversOfMouseEvent(MouseEvent event);
}
