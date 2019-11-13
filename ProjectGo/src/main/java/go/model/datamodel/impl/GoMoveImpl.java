package go.model.datamodel.impl;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;

import java.util.Objects;

public class GoMoveImpl implements GoMove {

    private GoPoint point;
    private StoneColor color;

    public GoMoveImpl(GoPoint point, StoneColor color) {
        this.point = point;
        this.color = color;
    }
    @Override
    public GoPoint getPoint() {
        return point;
    }

    @Override
    public StoneColor getStoneColor() {
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
