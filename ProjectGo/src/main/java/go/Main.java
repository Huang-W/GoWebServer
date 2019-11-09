package go;

import go.view.AppController;

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
		
		AppController goApp = AppController.getInstance();
		
	}
}
