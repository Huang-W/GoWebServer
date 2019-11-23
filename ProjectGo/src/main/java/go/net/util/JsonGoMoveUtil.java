package go.net.util;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import org.json.JSONObject;

/**
 * Contains utility methods for serializing Go moves as JSON.
 */
public final class JsonGoMoveUtil {
    /**
     * Creates a JSON object that describes a Go point.
     * It will have keys "x" and "y" with numbers describing the coordinates of the move.
     * @param point the point to serialize
     * @return A JSON object describing the point
     */
    public static final JSONObject serialize(GoPoint point) {
        JSONObject jsonGoPoint = new JSONObject();
        jsonGoPoint.put("x", point.getX());
        jsonGoPoint.put("y", point.getY());
        return jsonGoPoint;
    }

    /**
     * Creates a JSON object that describes a Go move.
     * It will have keys:
     * "point" - an object with the point the move was added to created by {@link JsonGoMoveUtil#serialize(GoPoint)}
     * "color" - value either "BLACK" or "WHITE" describing what color stone was added.
     * @param move the move to serialize
     * @return a JSON object describing the move
     */
    public static final JSONObject serialize(GoMove move) {
        JSONObject jsonGoMove = new JSONObject();
        jsonGoMove.put("point", serialize(move.getPoint()));
        jsonGoMove.put("color", move.getStoneColor().name());
        return jsonGoMove;
    }

}
