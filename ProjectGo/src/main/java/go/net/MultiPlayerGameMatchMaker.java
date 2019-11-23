package go.net;

import go.controller.GoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import go.net.socketstate.GoWebSocketStateMachine;

import java.util.ArrayDeque;
import java.util.Queue;

public class MultiPlayerGameMatchMaker {
    private Queue<GoWebSocketStateMachine> waitingPlayers;
    private static MultiPlayerGameMatchMaker INSTANCE = new MultiPlayerGameMatchMaker();
    public static MultiPlayerGameMatchMaker getInstance() {
        return INSTANCE;
    }
    private MultiPlayerGameMatchMaker() {
        this.waitingPlayers = new ArrayDeque<>();
    }
    public synchronized void enterQueueForGame(GoWebSocketStateMachine stateMachine) {
        System.out.println("player entered multiplayer queue. size: " + waitingPlayers.size());
        if (this.waitingPlayers.size() != 0) {
            GoWebSocketStateMachine other = this.waitingPlayers.poll();
            GoMoveController game = new GoMoveControllerImpl();
            other.joinTwoPlayerGameAsBlack(game, stateMachine);
            stateMachine.joinTwoPlayerGameAsWhite(game, other);
        } else {
            this.waitingPlayers.add(stateMachine);
        }
    }
}
