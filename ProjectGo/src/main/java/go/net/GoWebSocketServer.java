package go.net;

import go.net.socketstate.GoWebSocketStateMachine;
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
    private Map<WebSocket, GoWebSocketStateMachine> webSocketStates;
    public GoWebSocketServer(String hostname, int port) throws UnknownHostException {
        super (new InetSocketAddress(InetAddress.getByName(hostname), port));
        this.webSocketStates = new HashMap<>();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.printf("Open: from address %s with handshake resource descriptor %s\n",
                conn.getRemoteSocketAddress(),
                handshake.getResourceDescriptor());
        webSocketStates.put(conn, new GoWebSocketStateMachine(conn));
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.printf("Close: from address: %s\n",
                conn.getRemoteSocketAddress());
        GoWebSocketStateMachine disconnectingPlayer = webSocketStates.remove(conn);
        disconnectingPlayer.leaveMultiPlayerGame();
        conn.close(code, reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.printf("Message: from address %s: %s\n",
                conn.getRemoteSocketAddress(),
                message);
        try {
            GoWebSocketStateMachine stateMachine = webSocketStates.get(conn);
            JSONObject jsonMessage = new JSONObject(message);
            stateMachine.handleMessage(jsonMessage);
        } catch (JSONException exception) {
            System.out.printf("JSONException encountered: %s\n", exception.getMessage());
            exception.printStackTrace();
            conn.send(exception.getMessage());
        } catch (InvalidMessageException exception) {
            System.out.printf("InvalidMessageException encountered: %s\n", exception.getMessage());
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
                conn == null ? "null" : conn.getRemoteSocketAddress(),
                ex.getMessage());
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println(this.getClass().getName() + " started.");
    }
}
