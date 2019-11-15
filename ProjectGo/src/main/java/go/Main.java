package go;

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

	    ModelViewAdapter modelViewAdapter = new ModelViewAdapter();
	    ViewModelAdapter viewModelAdapter = new ViewModelAdapter();
	    modelViewAdapter.addViewObserver(viewModelAdapter);
	    viewModelAdapter.addGameObserver(modelViewAdapter);
	    viewModelAdapter.addMoveObserver(modelViewAdapter);
	    //ignore below
		GoViewController goViewController = new GoViewControllerImpl();
		GoMoveController goMoveController = new GoMoveControllerImpl();
		
	}
}
