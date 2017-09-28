package com.test.chess.board;

import com.test.chess.piece.Piece;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
class Move {

    private Piece piece;
    private Square destination;
    private boolean isCapture;

    static Move getValidMove(final String moveStr, final Square[][] chessboard,
                             final Map<Character, List<Piece>> auxPieceMap, final Fen fen) {
        char turn = fen.getSideToMove().charAt(0);
        if (turn == 'w' && !moveStr.matches("[KQNBR]?x?[a-h][1-8]")) {
            return null;
        }
        if (turn == 'b' && !moveStr.matches("[kqnbr]?x?[a-h][1-8]")) {
            return null;
        }
        Move move = new Move();
        int len = moveStr.length();
        if (moveStr.contains("x")) {
            move.isCapture = true;
            len--;
        }
        int sqRow = Square.getRow(Character.getNumericValue(moveStr.charAt(moveStr.length() - 1)));
        int sqCol = Square.getCol(moveStr.charAt(moveStr.length() - 2));
        Square square = chessboard[sqRow][sqCol];
        len -= 2;
        Piece piece;
        switch (len) {
            case 0:
                if (turn == 'w') {
                    piece = getValidPiece(auxPieceMap.get('P'), square, move.isCapture);
                } else {
                    piece = getValidPiece(auxPieceMap.get('p'), square, move.isCapture);
                }
                break;
            case 1:
                piece = getValidPiece(auxPieceMap.get(moveStr.charAt(0)), square, move.isCapture);
                break;
            default:
                piece = null;
                break;
        }

        if (piece == null) {
            return null;
        }
        move.destination = square;
        move.piece = piece;
        return move;
    }

    private static Piece getValidPiece(List<Piece> pieceList, Square destination, boolean isCapture) {
        for (Piece piece : pieceList) {
            if (piece.isValidMove(destination, isCapture)) {
                return piece;
            }
        }
        return null;
    }

    boolean isPawnMove() {
        return this.piece.getType() == 'P' || this.piece.getType() == 'p';
    }
}
