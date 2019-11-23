package go.net.constants;

/**
 * Class that houses string constants used as json keys in describing Go moves.
 */
public final class GoJSONConstants {
    public interface OUTBOUND_EVENT_TYPE {
        String KEY = "event_type";
        interface VALUES {
            String ADD_PIECE = "add_piece";
            String REMOVE_PIECE = "remove_piece";
            String GAME_END = "game_end";
            String ALERT = "alert";
            String USAGE_STATS = "usage_stats";
        }
        interface BODY_KEYS {
            String LIVE_USERS = "live_users";
            String USERS_IN_MULTIPLAYER = "users_in_multiplayer";
            String ALERT_MESSAGE = "alert_message";
        }
    }

    public interface INCOMING_EVENT_TYPE {
        String KEY = "event_type";
        interface VALUES {
            String MOVE = "move";
            String PASS = "pass";
            String UNDO = "undo";
            String SINGLE_PLAYER = "start_single_player";
            String MULTI_PLAYER = "start_multi_player";
        }
    }
    public interface MOVE_SERIALIZATION {
        String X = "x";
        String Y = "y";
        String COLOR = "color";
        String WINNER = "winner";
        String POINT = "point";
    }
}
