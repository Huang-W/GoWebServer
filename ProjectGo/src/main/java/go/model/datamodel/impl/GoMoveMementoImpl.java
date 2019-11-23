package go.model.datamodel.impl;

import go.model.datamodel.GoMove;
import go.model.datamodel.GoMoveMemento;

import java.util.Collection;

public class GoMoveMementoImpl implements GoMoveMemento {
    private Collection<GoMove> addedPieces;
    private Collection<GoMove> removedPieces;

    public GoMoveMementoImpl(Collection<GoMove> addedPieces, Collection<GoMove> removedPieces) {
        this.addedPieces = addedPieces;
        this.removedPieces = removedPieces;
    }


    @Override
    public Collection<GoMove> getAddedPieces() {
        return addedPieces;
    }

    @Override
    public Collection<GoMove> getRemovedPieces() {
        return removedPieces;
    }
}
