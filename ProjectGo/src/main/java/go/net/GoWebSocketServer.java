package go.net;

import go.controller.GoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Map;


/**
 * Class to handle websocket connections.
 * Reference: https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatServer.java
 * Library: https://github.com/TooTallNate/Java-WebSocket
 */
public class GoWebSocketServer extends WebSocketServer {
    private Map<WebSocket, GoMoveController> gamesByPlayer;
    public GoWebSocketServer(int port) {
        super (new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.printf("Open: from address %s with handshake resource descriptor %s\n",
                conn.getRemoteSocketAddress(),
                handshake.getResourceDescriptor());
        GoMoveController moveController = new GoMoveControllerImpl();
        gamesByPlayer.put(conn, moveController);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.printf("Close: from address: %s\n",
                conn.getRemoteSocketAddress());
        gamesByPlayer.remove(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.printf("Message: from address %s: %s\n",
                conn.getRemoteSocketAddress(),
                message);
        GoMoveController moveController = gamesByPlayer.get(conn);

        conn.send(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.printf("Error: from address %s: %s\n",
                conn.getRemoteSocketAddress(),
                ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println(this.getClass().getName() + " started.");
    }
}
