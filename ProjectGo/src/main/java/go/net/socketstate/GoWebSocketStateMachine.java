package go.net.socketstate;

import go.controller.GoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;
import go.net.InvalidMessageException;
import go.net.MultiPlayerGameMatchMaker;
import go.net.constants.GoJSONConstants;
import go.net.util.JsonGoMoveUtil;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class GoWebSocketStateMachine implements GoMoveObserver, GoGameObserver {
    private WebSocket webSocket;
    private GoWebSocketState state;
    private GoMoveController currentlyActiveGame;
    private GoWebSocketStateMachine otherPlayer;

    public GoWebSocketStateMachine(WebSocket webSocket) {
        this.webSocket = webSocket;
        this.state = new ConnectedPendingGameSelectionState(this);
    }
    public void handleMessage(JSONObject message) throws InvalidMessageException {
        String eventType = message.getString(GoJSONConstants.INCOMING_EVENT_TYPE.KEY);
        switch (eventType) {
            case GoJSONConstants.INCOMING_EVENT_TYPE.VALUES.MOVE:
                int x = message.getInt(GoJSONConstants.MOVE_SERIALIZATION.X);
                int y = message.getInt(GoJSONConstants.MOVE_SERIALIZATION.Y);
                state.handleMove(x, y);
                break;
            case GoJSONConstants.INCOMING_EVENT_TYPE.VALUES.PASS:
                state.handlePass();
                break;
            case GoJSONConstants.INCOMING_EVENT_TYPE.VALUES.UNDO:
                state.handleUndo();
                break;
            case GoJSONConstants.INCOMING_EVENT_TYPE.VALUES.SINGLE_PLAYER:
                state.handleJoinSinglePlayerGame();
                break;
            case GoJSONConstants.INCOMING_EVENT_TYPE.VALUES.MULTI_PLAYER:
                state.handleJoinTwoPlayerGame();
                break;
            default:
                throw new InvalidMessageException("No such message type " + eventType);
        }
    }

    public void joinSinglePlayerGame() {
        this.state = new InSinglePlayerGameState(this, currentlyActiveGame);
        currentlyActiveGame = new GoMoveControllerImpl();
        currentlyActiveGame.getGameSubject().addMoveObserver(this);
        currentlyActiveGame.getGameSubject().addGameObserver(this);
    }

    public void joinTwoPlayerGameQueue() {
        this.state = new WaitingGameState();
        MultiPlayerGameMatchMaker.getInstance().enterQueueForGame(this);
    }

    public void joinTwoPlayerGameAsBlack(GoMoveController moveController, GoWebSocketStateMachine otherPlayer) {
        System.out.println(this);
        System.out.println("joinTwoPlayerGameAsBlack");
        this.currentlyActiveGame = moveController;
        this.currentlyActiveGame.getGameSubject().addGameObserver(this);
        this.currentlyActiveGame.getGameSubject().addMoveObserver(this);
        this.otherPlayer = otherPlayer;
        this.state = new TwoPlayerGameMyTurnState(this, moveController);
    }

    public void joinTwoPlayerGameAsWhite(GoMoveController moveController, GoWebSocketStateMachine otherPlayer) {
        System.out.println(this);
        System.out.println("joinTwoPlayerGameAsWhite");
        this.currentlyActiveGame = moveController;
        this.currentlyActiveGame.getGameSubject().addGameObserver(this);
        this.currentlyActiveGame.getGameSubject().addMoveObserver(this);
        this.otherPlayer = otherPlayer;
        this.state = new WaitingGameState();
    }

    public void setMyTurn() {
        assert(this != this.otherPlayer);
        System.out.println(this);
        System.out.println("setMyTurn");
        this.state = new TwoPlayerGameMyTurnState(this, currentlyActiveGame);
    }
    public void multiPlayerMoveMade() {
        assert(this != this.otherPlayer);
        System.out.println(this);
        System.out.println("multiPlayerMoveMade");
        this.state = new WaitingGameState();
        this.otherPlayer.setMyTurn();
    }

    @Override
    public void handleGameEnd(StoneColor winner) {
        JSONObject jsonWinner = new JSONObject();
        jsonWinner.put(GoJSONConstants.MOVE_SERIALIZATION.WINNER, winner.name());
        jsonWinner.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.KEY, GoJSONConstants.OUTBOUND_EVENT_TYPE.VALUES.GAME_END);
        webSocket.send(jsonWinner.toString());
    }

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        JSONObject jsonMove = JsonGoMoveUtil.serialize(move);
        jsonMove.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.KEY, GoJSONConstants.OUTBOUND_EVENT_TYPE.VALUES.ADD_PIECE);
        webSocket.send(jsonMove.toString());
    }

    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        JSONObject goPoint = JsonGoMoveUtil.serialize(point);
        JSONObject message = new JSONObject();
        message.put(GoJSONConstants.OUTBOUND_EVENT_TYPE.KEY, GoJSONConstants.OUTBOUND_EVENT_TYPE.VALUES.REMOVE_PIECE);
        message.put(GoJSONConstants.MOVE_SERIALIZATION.POINT, goPoint);
        webSocket.send(message.toString());
    }
}
