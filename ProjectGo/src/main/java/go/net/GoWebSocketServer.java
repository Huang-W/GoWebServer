package go.net;

import go.controller.GoMoveController;
import go.controller.JsonGoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import go.net.impl.WebSocketGoMoveController;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * Class to handle websocket connections.
 * Reference: https://github.com/TooTallNate/Java-WebSocket/blob/master/src/main/example/ChatServer.java
 * Library: https://github.com/TooTallNate/Java-WebSocket
 */
public class GoWebSocketServer extends WebSocketServer {
    private Map<WebSocket, JsonGoMoveController> gamesByPlayer;
    public GoWebSocketServer(String hostname, int port) throws UnknownHostException {
        super (new InetSocketAddress(InetAddress.getByName(hostname), port));
        this.gamesByPlayer = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.printf("Open: from address %s with handshake resource descriptor %s\n",
                conn.getRemoteSocketAddress(),
                handshake.getResourceDescriptor());
        JsonGoMoveController moveController = new WebSocketGoMoveController(conn);
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
        try {
            JsonGoMoveController moveController = gamesByPlayer.get(conn);
            JSONObject jsonMessage = new JSONObject(message);
            moveController.handleMove(jsonMessage);
        } catch (JSONException exception) {
            System.out.printf("JSONException encountered: %s\n", exception.getMessage());
            exception.printStackTrace();
            conn.send(exception.getMessage());
        } catch (InvalidMessageException exception) {
            System.out.printf("INvalidMessageException encountered: %s\n", exception.getMessage());
            exception.printStackTrace();
            conn.send(exception.getMessage());
        } catch (RuntimeException exception) {
            System.out.printf("Unexpected exception encountered of type %s: %s\n", exception.getClass().getName(), exception.getMessage());
            exception.printStackTrace();
            conn.send(exception.getMessage());
        }
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
