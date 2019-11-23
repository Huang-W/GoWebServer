package go.net;

import go.net.constants.GoJSONConstants;
import go.net.socketstate.GoWebSocketStateMachine;
import go.net.usage.UsageObserver;
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
public class GoWebSocketServer extends WebSocketServer implements UsageObserver {
    private Map<WebSocket, GoWebSocketStateMachine> webSocketStates;
    public GoWebSocketServer(String hostname, int port) throws UnknownHostException {
        super (new InetSocketAddress(InetAddress.getByName(hostname), port));

        this.webSocketStates = new HashMap<>();
        MultiPlayerGameMatchMaker.getInstance().addUsageObserver(this);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.printf("Open: from address %s with handshake resource descriptor %s\n",
                conn.getRemoteSocketAddress(),
                handshake.getResourceDescriptor());
        GoWebSocketStateMachine stateMachine = new GoWebSocketStateMachine(conn);
        webSocketStates.put(conn, stateMachine);
        broadcastUsageStatistics();
        stateMachine.alert("Welcome to Go! Select a game to get started.");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.printf("Close: from address: %s\n",
                conn.getRemoteSocketAddress());
        GoWebSocketStateMachine disconnectingPlayer = webSocketStates.remove(conn);
        disconnectingPlayer.leaveMultiPlayerGameIfNecessary();
        conn.close(code, reason);
        broadcastUsageStatistics();
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

    public void broadcastUsageStatistics() {
        JSONObject usageStatistics = new JSONObject();
        usageStatistics.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.KEY, GoJSONConstants.OUTBOUND_EVENT_TYPE.VALUES.USAGE_STATS);
        long usersOnline = webSocketStates.size();
        long usersInMultiplayer = webSocketStates.values().stream()
                .filter(GoWebSocketStateMachine::isInMultiplayer)
                .count() +
                MultiPlayerGameMatchMaker.getInstance().getWaitingUsers();

        usageStatistics.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.BODY_KEYS.LIVE_USERS, usersOnline);
        usageStatistics.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.BODY_KEYS.USERS_IN_MULTIPLAYER, usersInMultiplayer);
        broadcast(usageStatistics.toString());
    }

    @Override
    public void usageUpdated() {
        broadcastUsageStatistics();
    }
}
