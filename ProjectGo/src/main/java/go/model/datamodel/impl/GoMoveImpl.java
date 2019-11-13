package go.model.datamodel.impl;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoPoint;
import go.model.datamodel.StoneColor;

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
}
