package go;

import go.adapter.ModelViewAdapter;
import go.adapter.ViewModelAdapter;
import go.controller.*;
import go.controller.impl.*;
import go.net.GoWebSocketServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 * Builds UI and starts the game.
 *
 */
public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length == 0) {
		    //Schedule a job for the event-dispatching thread:
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    init();
                }
            });
        } else if ("server".equals(args[0])) {
            runWebSocketServer();
        }
	}


	// Reference: https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatServer.java
    private static void runWebSocketServer() throws IOException, InterruptedException {
        String hostname = getHostNameFromFile();
        int port = getPortNumberFromFile();
        System.out.println(hostname  + ":" + port);
        GoWebSocketServer server = new GoWebSocketServer(hostname, port);
        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        server.start();
        while ( true ) {
            String in = sysin.readLine();
            if( in.equals( "exit" ) ) {
                server.stop(1000);
                break;
            }
        }
    }
    private static String getHostNameFromFile() throws IOException{
	    File f = new File("host");
	    BufferedReader reader = new BufferedReader(new FileReader(f));
	    return reader.readLine();
    }
    private static int getPortNumberFromFile() throws IOException{
        File f = new File("port");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        return Integer.parseInt(reader.readLine());
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
