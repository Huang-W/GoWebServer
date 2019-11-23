package go.net.util;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;
import go.model.datamodel.impl.GoMoveImpl;
import go.model.datamodel.impl.GoPointImpl;
import go.net.InvalidMessageException;
import go.net.constants.GoJSONConstants;
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
        jsonGoPoint.put(GoJSONConstants.MOVE_SERIALIZATION.X, point.getX());
        jsonGoPoint.put(GoJSONConstants.MOVE_SERIALIZATION.Y, point.getY());
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
        jsonGoMove.put(GoJSONConstants.MOVE_SERIALIZATION.POINT, serialize(move.getPoint()));
        jsonGoMove.put(GoJSONConstants.MOVE_SERIALIZATION.COLOR, move.getStoneColor().name());
        return jsonGoMove;
    }

    /**
     * Creates a GoMove from a JSON object describing it.
     * The JSON Object should follow the format as if it was created by {@link JsonGoMoveUtil#serialize(GoMove)}
     * @param move the JSON object describing the move
     * @return a GoMove
     */
    public static final GoMove deserializeMove(JSONObject move) throws InvalidMessageException{
        JSONObject jsonGoPoint = move.getJSONObject(GoJSONConstants.MOVE_SERIALIZATION.POINT);
        GoPoint point = deserializePoint(jsonGoPoint);
        String colorString = move.getString(GoJSONConstants.MOVE_SERIALIZATION.COLOR);
        StoneColor color = StoneColor.fromString(colorString);
        if (null == color) {
            throw new InvalidMessageException("No such color " + colorString);
        }
        return new GoMoveImpl(point, color);
    }
    /**
     * Creates a GoPoint from a JSON object describing it.
     * The JSON Object should follow the format as if it was created by {@link JsonGoMoveUtil#serialize(GoPoint)}
     * @param point the JSON object describing the move
     * @return a GoPoint
     */
    public static final GoPoint deserializePoint(JSONObject point) {
        int x = point.getInt(GoJSONConstants.MOVE_SERIALIZATION.X);
        int y = point.getInt(GoJSONConstants.MOVE_SERIALIZATION.Y);

        return GoPointImpl.of(x, y);
    }


}
