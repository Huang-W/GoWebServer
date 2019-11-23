package go.net.impl;

import go.controller.GoMoveController;
import go.controller.JsonGoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.observer.GoGameObserver;
import go.model.observer.GoMoveObserver;
import go.net.InvalidMessageException;
import go.net.util.JsonGoMoveUtil;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class WebSocketGoMoveControllerImpl implements JsonGoMoveController, GoMoveObserver, GoGameObserver {
    private GoMoveController goMoveController;
    private WebSocket webSocket;

    public WebSocketGoMoveControllerImpl(WebSocket webSocket) {
        this.webSocket = webSocket;
        this.goMoveController = new GoMoveControllerImpl();
        goMoveController.getGameSubject().addGameObserver(this);
        goMoveController.getGameSubject().addMoveObserver(this);
    }

    @Override
    public void handleMove(JSONObject jsonObject) throws InvalidMessageException {
        String eventType = jsonObject.getString("")
    }

    @Override
    public void handleGameEnd(StoneColor winner) {
        JSONObject jsonWinner = new JSONObject();
        jsonWinner.put("winner", winner.name());
        webSocket.send(jsonWinner.toString());
    }

    @Override
    public void handlePieceAdditionEvent(GoMove move) {
        JSONObject jsonMove = JsonGoMoveUtil.serialize(move);
        webSocket.send(jsonMove.toString());
    }

    @Override
    public void handlePieceRemovalEvent(GoPoint point) {
        JSONObject goPoint = JsonGoMoveUtil.serialize(point);
        webSocket.send(goPoint.toString());
    }
}
