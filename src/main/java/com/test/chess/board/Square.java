package com.test.chess.board;

import com.test.chess.piece.Piece;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square {

    private Piece piece;
    private int rank;
    private char file;
    private int row;
    private int col;
    private boolean isVacant;

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.rank = 8 - row;
        this.file = (char) ('a' + col);
        this.isVacant = true;
    }

    static int getRow(int rank) {
        return 8 - rank;
    }

    static int getCol(char file) {
        return (int) file - 97;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.isVacant = false;
    }
}