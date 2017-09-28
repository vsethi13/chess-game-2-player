package com.test.chess.piece;

import com.test.chess.board.Square;

public class Knight extends Piece {

    public Knight(char type) {
        super(type);
    }

    @Override
    public boolean isValidMove(Square destination, boolean isCapture) {
        return isDestinationInLimits(destination) &&
                isDestinationPathClear(destination, isCapture);
    }

    private boolean isDestinationInLimits(Square destination) {
        Square source = this.getSquare();
        int rDiff = Math.abs(destination.getRow() - source.getRow());
        int cDiff = Math.abs(destination.getCol() - source.getCol());
        return rDiff == 2 && cDiff == 1 || cDiff == 2 && rDiff == 1;
    }

    private boolean isDestinationPathClear(Square destination, boolean isCapture) {
        if (isCapture && destination.isVacant()) {
            return false;
        }
        if (!isCapture && !destination.isVacant()) {
            return false;
        }
        return true;
    }
}
