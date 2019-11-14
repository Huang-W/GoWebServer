package go.view.datamodel.impl;

import go.view.datamodel.GoMove;
import java.awt.Color;
import java.awt.Point;
import java.util.Objects;

public class GoMoveImpl implements GoMove {

    private Point point;
    private Color color;

    public GoMoveImpl(Point point, Color color) {
        this.point = point;
        this.color = color;
    }
    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public Color getStoneColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoMoveImpl goMove = (GoMoveImpl) o;
        return Objects.equals(point, goMove.point) &&
                color == goMove.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }

    @Override
    public String toString() {
        return "GoMoveImpl{" +
                "point=" + point +
                ", color=" + color +
                '}';
    }
}
