package go.view.observer;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public interface GoScreenObserver {

	void handleActionEvent(ActionEvent event);
	
	void handleMouseEvent(MouseEvent event);
}
