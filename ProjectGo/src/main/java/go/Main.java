package go;

import go.adapter.ModelViewAdapter;
import go.adapter.ViewModelAdapter;
import go.controller.*;
import go.controller.impl.*;

/**
 * Builds UI and starts the game.
 *
 */
public class Main {
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				init();
			}
		});
	}

	private static void init() {
		GoViewController goViewController = new GoViewControllerImpl();
		GoMoveController goMoveController = new GoMoveControllerImpl();

		ModelViewAdapter modelViewAdapter = new ModelViewAdapter(goViewController);
		ViewModelAdapter viewModelAdapter = new ViewModelAdapter(goMoveController);

		goViewController.getViewSubject().addViewObserver(viewModelAdapter);
		goViewController.getViewConfigSubject().addViewConfigObserver(viewModelAdapter);
		goMoveController.getGameSubject().addGameObserver(modelViewAdapter);
		goMoveController.getGameSubject().addMoveObserver(modelViewAdapter);
		goMoveController.getGameSubject().addModelConfigObserver(modelViewAdapter);
	}
}
