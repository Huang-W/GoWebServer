package go.net;

/**
 * An exception that is thrown when invalid messages are
 * received over a websocket. Such messages can be malformed JSON or not contain expected values.
 */
public class InvalidMessageException extends Exception {
    public InvalidMessageException(String message) {
        super(message);
    }
}
