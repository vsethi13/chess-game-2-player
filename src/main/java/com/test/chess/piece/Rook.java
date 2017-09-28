package com.test.chess.piece;

import com.test.chess.board.ChessBoard;
import com.test.chess.board.Square;

public class Rook extends Piece {

    public Rook(char type) {
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
        return rDiff > 0 && cDiff == 0 || cDiff > 0 && rDiff == 0;
    }

    private boolean isDestinationPathClear(Square destination, boolean isCapture) {
        Square source = this.getSquare();
        int rDiff = destination.getRow() - source.getRow();
        int cDiff = destination.getCol() - source.getCol();
        Square temp = source;
        int rowFlag = 0;
        int colFlag = 0;
        int counter = 0;
        // rDiff is negative for up move and positive for down move
        // cDiff is negative for left move and positive for right move
        if (rDiff != 0) {
            rowFlag = rDiff > 0 ? 1 : -1;
            counter = rDiff > 0 ? rDiff : rDiff * -1;
        }
        if (cDiff != 0) {
            colFlag = cDiff > 0 ? 1 : -1;
            counter = cDiff > 0 ? cDiff : cDiff * -1;
        }
        while (counter > 1) {   // leave last block to check for isCapture
            temp = ChessBoard.getAdjSquare(temp, rowFlag, colFlag);
            if (!temp.isVacant()) {
                return false;
            }
            counter--;
        }
        temp = ChessBoard.getAdjSquare(temp, rowFlag, colFlag);
        if (isCapture && temp.isVacant()) {
            return false;
        }
        if (!isCapture && !temp.isVacant()) {
            return false;
        }
        return true;
    }
}
