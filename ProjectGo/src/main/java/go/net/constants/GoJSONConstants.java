package go.net.constants;

/**
 * Class that houses string constants used as json keys in describing Go moves.
 */
public final class GoJSONConstants {
    interface EVENT_TYPE {
        String KEY = "event_type";
        interface VALUES {
            String MOVE = "move";
            String PASS = "pass";
            String UNDO = "undo";
        }
    }
    interface MOVE_SERIALIZATION {

    }
}
