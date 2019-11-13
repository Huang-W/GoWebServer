package go.model.datamodel.impl;

import go.model.datamodel.GoPoint;

import java.util.Objects;

public class GoPointImpl implements GoPoint {
    private final int x;
    private final int y;
    public GoPointImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static GoPointImpl of(int x, int y) {
        return new GoPointImpl(x, y);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoPointImpl goPoint = (GoPointImpl) o;
        return x == goPoint.x &&
                y == goPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "GoPointImpl{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
