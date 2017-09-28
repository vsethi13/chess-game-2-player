package com.test.chess.stub;

import com.test.chess.board.ChessBoard;
import com.test.chess.board.Fen;
import com.test.chess.board.Square;
import com.test.chess.piece.Piece;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ChessBoardStub {

    public static Square[][] chessBoard;
    public static Map<Character, List<Piece>> auxPieceMap;
    public static Fen fen;

    public static void initializeChess() throws Exception {
        ChessBoard.initiate("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        setFields();
    }

    public static void emptyChessBoard() throws Exception {
        ChessBoard.initiate("8/8/8/8/8/8/8/8 w KQkq - 0 1");
        setFields();
    }

    public static void destroyChessBoard() throws Exception {
        Field chessBoardField = ChessBoard.class.getDeclaredField("chessBoard");
        chessBoardField.setAccessible(true);
        chessBoardField.set(null, null);

        Field auxPieceMapField = ChessBoard.class.getDeclaredField("auxPieceMap");
        auxPieceMapField.setAccessible(true);
        auxPieceMapField.set(null, null);

        Field fenField = ChessBoard.class.getDeclaredField("fen");
        fenField.setAccessible(true);
        fenField.set(null, null);
    }

    private static void setFields() throws Exception {
        // get private fields for testing
        Field chessBoardField = ChessBoard.class.getDeclaredField("chessBoard");
        chessBoardField.setAccessible(true);
        chessBoard = (Square[][]) chessBoardField.get(null);

        Field auxPieceMapField = ChessBoard.class.getDeclaredField("auxPieceMap");
        auxPieceMapField.setAccessible(true);
        auxPieceMap = (Map<Character, List<Piece>>) auxPieceMapField.get(null);

        Field fenField = ChessBoard.class.getDeclaredField("fen");
        fenField.setAccessible(true);
        fen = (Fen) fenField.get(null);
    }
}
