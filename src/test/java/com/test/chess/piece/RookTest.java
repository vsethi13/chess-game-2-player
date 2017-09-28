package com.test.chess.piece;

import com.test.chess.stub.ChessBoardStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    private Rook rook;

    @Before
    public void setUp() throws Exception {
        rook = new Rook('R');
    }

    @Test
    public void isValidMoveTest1() throws Exception {
        ChessBoardStub.initializeChess();
        rook = (Rook) ChessBoardStub.chessBoard[7][0].getPiece();

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][0], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][4], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[7][0], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[7][1], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[6][0], false)).isFalse();
    }

    @Test
    public void isValidMoveTest2() throws Exception {
        ChessBoardStub.emptyChessBoard();
        rook.setSquare(ChessBoardStub.chessBoard[4][4]);
        ChessBoardStub.chessBoard[4][4].setPiece(rook);

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][7], false)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][0], false)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[0][4], false)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[7][4], false)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[5][5], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[3][3], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[5][3], false)).isFalse();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[3][5], false)).isFalse();
    }

    @Test
    public void isValidMoveTest3() throws Exception {
        ChessBoardStub.emptyChessBoard();
        rook.setSquare(ChessBoardStub.chessBoard[4][4]);
        ChessBoardStub.chessBoard[4][4].setPiece(rook);

        Pawn pawn1 = new Pawn('p');
        pawn1.setSquare(ChessBoardStub.chessBoard[4][7]);
        ChessBoardStub.chessBoard[4][7].setPiece(pawn1);

        Pawn pawn2 = new Pawn('p');
        pawn2.setSquare(ChessBoardStub.chessBoard[4][0]);
        ChessBoardStub.chessBoard[4][0].setPiece(pawn2);

        Pawn pawn3 = new Pawn('p');
        pawn3.setSquare(ChessBoardStub.chessBoard[7][4]);
        ChessBoardStub.chessBoard[7][4].setPiece(pawn3);

        Pawn pawn4 = new Pawn('p');
        pawn4.setSquare(ChessBoardStub.chessBoard[0][4]);
        ChessBoardStub.chessBoard[0][4].setPiece(pawn4);

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][7], true)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][7], false)).isFalse();

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][0], true)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[4][0], false)).isFalse();

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[7][4], true)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[7][4], false)).isFalse();

        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[0][4], true)).isTrue();
        assertThat(rook.isValidMove(ChessBoardStub.chessBoard[0][4], false)).isFalse();
    }

    @After
    public void tearDown() throws Exception {
        ChessBoardStub.destroyChessBoard();
    }
}