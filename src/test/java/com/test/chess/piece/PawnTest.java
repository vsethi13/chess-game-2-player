package com.test.chess.piece;

import com.test.chess.stub.ChessBoardStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    private Pawn blackPawn;
    private Pawn whitePawn;

    @Before
    public void setUp() throws Exception {
        blackPawn = new Pawn('p');
        whitePawn = new Pawn('P');
    }

    @Test
    public void isValidMoveTest1() throws Exception {
        ChessBoardStub.initializeChess();
        blackPawn = (Pawn) ChessBoardStub.chessBoard[1][3].getPiece();
        whitePawn = (Pawn) ChessBoardStub.chessBoard[6][7].getPiece();

        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[4][4], false)).isFalse();
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[4][4], true)).isFalse();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[4][4], false)).isFalse();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[4][4], true)).isFalse();

        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[5][7], false)).isTrue();
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[4][7], false)).isTrue();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[2][3], false)).isTrue();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[3][3], false)).isTrue();
    }

    @Test
    public void isValidMoveTest2() throws Exception {
        ChessBoardStub.emptyChessBoard();
        whitePawn.setSquare(ChessBoardStub.chessBoard[4][5]);
        ChessBoardStub.chessBoard[4][5].setPiece(whitePawn);
        blackPawn.setSquare(ChessBoardStub.chessBoard[4][3]);
        ChessBoardStub.chessBoard[4][3].setPiece(blackPawn);

        // move ahead test
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[3][5], false)).isTrue();
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[5][5], false)).isFalse();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[3][3], false)).isFalse();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[5][3], false)).isTrue();

        // double jump test
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[2][5], false)).isFalse();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[6][3], false)).isFalse();

        // obstruction test
        ChessBoardStub.chessBoard[3][5].setVacant(false);
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[3][5], false)).isFalse();
        ChessBoardStub.chessBoard[5][3].setVacant(false);
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[5][3], false)).isFalse();
    }

    @Test
    public void isValidMoveTest3() throws Exception {
        ChessBoardStub.emptyChessBoard();
        whitePawn.setSquare(ChessBoardStub.chessBoard[4][5]);
        Pawn bp1 = new Pawn('p');
        bp1.setSquare(ChessBoardStub.chessBoard[3][4]);
        ChessBoardStub.chessBoard[3][4].setPiece(bp1);
        Pawn bp2 = new Pawn('p');
        bp2.setSquare(ChessBoardStub.chessBoard[3][6]);
        ChessBoardStub.chessBoard[3][6].setPiece(bp2);

        blackPawn.setSquare(ChessBoardStub.chessBoard[4][3]);
        Pawn wp1 = new Pawn('P');
        wp1.setSquare(ChessBoardStub.chessBoard[5][4]);
        ChessBoardStub.chessBoard[5][4].setPiece(wp1);
        Pawn wp2 = new Pawn('P');
        wp2.setSquare(ChessBoardStub.chessBoard[5][2]);
        ChessBoardStub.chessBoard[5][2].setPiece(wp2);

        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[3][4], true)).isTrue();
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[3][6], true)).isTrue();
        assertThat(whitePawn.isValidMove(ChessBoardStub.chessBoard[4][3], true)).isFalse();

        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[5][4], true)).isTrue();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[5][2], true)).isTrue();
        assertThat(blackPawn.isValidMove(ChessBoardStub.chessBoard[4][5], true)).isFalse();
    }

    @After
    public void tearDown() throws Exception {
        ChessBoardStub.destroyChessBoard();
    }
}