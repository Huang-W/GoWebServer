package go.model.datamodel;

/**
 * The color of one stone on a Go board.
 */
public enum StoneColor {
    BLACK, WHITE;
    public static StoneColor fromString(String color) {
        if (StoneColor.BLACK.name().equals(color)) {
            return StoneColor.BLACK;
        } else if (StoneColor.WHITE.name().equals(color)) {
            return StoneColor.WHITE;
        }
        return null;
    }
}
