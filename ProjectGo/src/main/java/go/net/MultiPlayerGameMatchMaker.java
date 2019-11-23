package go.net;

import go.controller.GoMoveController;
import go.controller.impl.GoMoveControllerImpl;
import go.net.socketstate.GoWebSocketStateMachine;
import go.net.usage.UsageObserver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MultiPlayerGameMatchMaker {
    private List<UsageObserver> observers;
    private Queue<GoWebSocketStateMachine> waitingPlayers;
    private static MultiPlayerGameMatchMaker INSTANCE = new MultiPlayerGameMatchMaker();
    public static MultiPlayerGameMatchMaker getInstance() {
        return INSTANCE;
    }
    private MultiPlayerGameMatchMaker() {
        this.waitingPlayers = new ArrayDeque<>();
        this.observers = new ArrayList<>();
    }
    public synchronized void enterQueueOrLeaveIfAlreadyEnqueued(GoWebSocketStateMachine stateMachine) {
        System.out.println("player entered multiplayer queue. size: " + waitingPlayers.size());
        if (this.waitingPlayers.size() != 0) {
            GoWebSocketStateMachine other = this.waitingPlayers.poll();
            GoMoveController game = new GoMoveControllerImpl();
            other.joinTwoPlayerGameAsBlack(game, stateMachine);
            stateMachine.joinTwoPlayerGameAsWhite(game, other);
        } else {
            this.waitingPlayers.add(stateMachine);
            stateMachine.alert("No players are currently searching for a multiplayer opponent, but we will find you one as soon as we can!");
        }
        observers.forEach(UsageObserver::usageUpdated);
    }
    public void addUsageObserver(UsageObserver observer) {
        this.observers.add(observer);
    }
    public int getWaitingUsers() {
        return waitingPlayers.size();
    }
}
