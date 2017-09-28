package com.test.chess.piece;

import com.test.chess.board.ChessBoard;
import com.test.chess.board.Square;

public class Pawn extends Piece {

    public Pawn(char type) {
        super(type);
    }

    @Override
    public boolean isValidMove(Square destination, boolean isCapture) {
        return isDestinationAhead(destination) &&
                isDestinationInLimits(destination, isCapture) &&
                isDestinationPathClear(destination, isCapture);
    }

    private boolean isDestinationAhead(Square destination) {
        Square source = this.getSquare();
        return this.type == 'P' && destination.getRow() < source.getRow() ||
                this.type == 'p' && destination.getRow() > source.getRow();
    }

    private boolean isDestinationInLimits(Square destination, boolean isCapture) {
        Square source = this.getSquare();
        int rDiff = Math.abs(destination.getRow() - source.getRow());
        int cDiff = Math.abs(destination.getCol() - source.getCol());
        if (!isCapture) {
            return cDiff == 0 &&
                    (rDiff == 1 ||
                            rDiff == 2 && (this.type == 'P' && source.getRow() == 6 || this.type == 'p' && source.getRow() == 1));
        } else {
            return rDiff == 1 && cDiff == 1;
        }
    }

    private boolean isDestinationPathClear(Square destination, boolean isCapture) {
        Square source = this.getSquare();
        int rDiff = destination.getRow() - source.getRow();
        int cDiff = destination.getCol() - source.getCol();
        Square temp = source;
        // rDiff is negative for w player and positive for b player
        int rowFlag = rDiff > 0 ? 1 : -1;
        int colFlag = cDiff > 0 ? 1 : -1;
        if (!isCapture) {
            int counter = rDiff > 0 ? rDiff : rDiff * -1;
            while (counter > 0) {
                temp = ChessBoard.getAdjSquare(temp, rowFlag, 0);
                if (!temp.isVacant()) {
                    return false;
                }
                counter--;
            }
            return true;
        } else {
            temp = ChessBoard.getAdjSquare(source, rowFlag, colFlag);
            return !temp.isVacant();
        }
    }
}
