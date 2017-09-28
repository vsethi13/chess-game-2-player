package com.test.chess.piece;

import com.test.chess.board.Square;

public class King extends Piece {

    public King(char type) {
        super(type);
    }

    @Override
    public boolean isValidMove(Square destination, boolean isCapture) {
        return false;
    }
}
