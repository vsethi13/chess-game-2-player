package com.test.chess.piece;

import com.test.chess.board.Square;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Piece {

    char type;
    Square square;

    public Piece(char type) {
        this.type = type;
    }

    public abstract boolean isValidMove(Square destination, boolean isCapture);
}
