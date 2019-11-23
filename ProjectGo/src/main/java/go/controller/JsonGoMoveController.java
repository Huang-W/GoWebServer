package go.controller;

import org.json.JSONObject;

/**
 * Handles Go Moves described in JSON.
 */
public interface JsonGoMoveController {
    /**
     * Handles the move described by JSON.
     * This move is described by its top-level "type" key -
     * "move" - this JSON Object describes the next player making a move.
     *      Such an object should also have keys "x" and "y" with numbers describing the coordinates of this move on the board.
     * "pass" = this JSON Object describes the next player passing
     * "undo" - this JSON Object describes a request to undo a move.
     * @todo - reset and resize support
     * @param jsonObject The object describing the move.
     */
    void handleMove(JSONObject jsonObject);
}
