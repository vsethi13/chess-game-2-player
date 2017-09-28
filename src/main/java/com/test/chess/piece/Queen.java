package com.test.chess.piece;

import com.test.chess.board.Square;

public class Queen extends Piece {

    public Queen(char type) {
        super(type);
    }

    @Override
    public boolean isValidMove(Square destination, boolean isCapture) {
        return getBishopCharacteristics().isValidMove(destination, isCapture) || getRookCharacteristics().isValidMove(destination, isCapture);
    }

    private Bishop getBishopCharacteristics() {
        Bishop bishop = new Bishop(type);
        bishop.setSquare(this.square);
        return bishop;
    }

    private Rook getRookCharacteristics() {
        Rook rook = new Rook(type);
        rook.setSquare(this.square);
        return rook;
    }
}
