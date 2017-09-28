package com.test.chess.board;

import com.test.chess.piece.Piece;
import com.test.chess.stub.ChessBoardStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Before
    public void setUp() throws Exception {
        ChessBoardStub.initializeChess();
    }

    @Test
    public void moveTest() throws Exception {
        ChessBoard.move("e4");
        assertThat(ChessBoardStub.fen.toString()).isEqualTo("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        assertThat(ChessBoardStub.chessBoard[4][4].getPiece().getType()).isEqualTo('P');
        assertThat(ChessBoardStub.chessBoard[4][4].getPiece().getSquare().getRow()).isEqualTo(4);
        assertThat(ChessBoardStub.chessBoard[4][4].getPiece().getSquare().getCol()).isEqualTo(4);
        assertThat(ChessBoardStub.chessBoard[4][4].getPiece().getSquare().getFile()).isEqualTo('e');
        assertThat(ChessBoardStub.chessBoard[4][4].getPiece().getSquare().getRank()).isEqualTo(4);

        ChessBoard.move("c5");
        assertThat(ChessBoardStub.fen.toString()).isEqualTo("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");
        ChessBoard.move("Nf3");
        assertThat(ChessBoardStub.fen.toString()).isEqualTo("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
    }

    @Test
    public void captureTest() throws Exception {
        List<Piece> bpList = ChessBoardStub.auxPieceMap.get('p');
        assertThat(bpList).hasSize(8);
        ChessBoard.move("e4");
        ChessBoard.move("c5");
        ChessBoard.move("Nf3");
        ChessBoard.move("d5");
        ChessBoard.move("xd5");
        assertThat(ChessBoardStub.fen.toString()).isEqualTo("rnbqkbnr/pp2pppp/8/2pP4/8/5N2/PPPP1PPP/RNBQKB1R b KQkq - 0 3");
        assertThat(bpList).hasSize(7);
    }

    @After
    public void tearDown() throws Exception {
        ChessBoardStub.destroyChessBoard();
    }
}