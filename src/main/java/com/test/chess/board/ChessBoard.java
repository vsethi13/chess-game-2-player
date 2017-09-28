package com.test.chess.board;

import com.test.chess.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static Square[][] chessBoard;
    private static Map<Character, List<Piece>> auxPieceMap;
    private static Fen fen;

    private ChessBoard() {
    }

    public static void move(String moveStr) {
        Move move = Move.getValidMove(moveStr, chessBoard, auxPieceMap, fen);
        if (move != null) {
            performMove(move);
        } else {
            System.out.println("Invalid move.");
        }
        System.out.println("Next player to move: " + fen.getSideToMove());
    }

    private static void performMove(Move move) {
        if (move.isCapture()) {
            Piece loser = move.getDestination().getPiece();
            Piece winner = move.getPiece();
            if ((Character.isUpperCase(loser.getType()) && Character.isUpperCase(winner.getType())) ||
                    Character.isLowerCase(loser.getType()) && Character.isLowerCase(winner.getType())) {
                System.out.println("Friendly fire not allowed");
                return;
            }
            auxPieceMap.get(loser.getType()).remove(loser);
        }
        Piece piece = move.getPiece();
        Square sourceSquare = move.getPiece().getSquare();
        Square destinationSquare = move.getDestination();

        //update source square
        sourceSquare.setPiece(null);
        sourceSquare.setVacant(true);

        //update destination square
        destinationSquare.setPiece(move.getPiece());
        destinationSquare.setVacant(false);

        //update the piece
        piece.setSquare(destinationSquare);

        //update fen
        fen.regenerateRank(sourceSquare.getRow(), chessBoard[sourceSquare.getRow()]);
        if (sourceSquare.getRow() != destinationSquare.getRow()) {
            fen.regenerateRank(destinationSquare.getRow(), chessBoard[destinationSquare.getRow()]);
        }
        fen.toggleTurnAndIncCounters(move.isCapture() || move.isPawnMove());
        fen.setEnPassantTargetSquare(piece, sourceSquare, destinationSquare);
    }

    public static Square getAdjSquare(Square source, int rowFlag, int colFlag) {
        Square sq = null;
        int row = 0;
        int col = 0;
        if (rowFlag != 0) {
            row = rowFlag > 0 ? 1 : -1;
        }
        if (colFlag != 0) {
            col = colFlag > 0 ? 1 : -1;
        }
        int newRow = source.getRow() + row;
        int newCol = source.getCol() + col;
        if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
            sq = chessBoard[newRow][newCol];
        }
        return sq;
    }

    public static void initiate(String fenString) {
        chessBoard = new Square[8][8];
        auxPieceMap = new HashMap<>(12);
        auxPieceMap.put('r', new ArrayList<>(2));
        auxPieceMap.put('n', new ArrayList<>(2));
        auxPieceMap.put('b', new ArrayList<>(2));
        auxPieceMap.put('q', new ArrayList<>(1));
        auxPieceMap.put('k', new ArrayList<>(1));
        auxPieceMap.put('p', new ArrayList<>(8));
        auxPieceMap.put('R', new ArrayList<>(2));
        auxPieceMap.put('N', new ArrayList<>(2));
        auxPieceMap.put('B', new ArrayList<>(2));
        auxPieceMap.put('Q', new ArrayList<>(1));
        auxPieceMap.put('K', new ArrayList<>(1));
        auxPieceMap.put('P', new ArrayList<>(8));
        fen = new Fen(fenString);
        String[] ranks = fen.getRankArray();
        for (int i = 0; i < ranks.length; i++) {
            setChessboardRank(i, ranks[i]);
        }
    }

    private static void setChessboardRank(int row, String rank) {
        int counter = 0;
        for (int j = 0; j < 8; j++, counter++) {
            Square sq = new Square(row, j);
            char c = rank.charAt(counter);
            if (Character.isDigit(c)) {
                counter--;
            } else {
                Piece piece;
                switch (c) {
                    case 'r':
                    case 'R':
                        piece = new Rook(c);
                        break;
                    case 'n':
                    case 'N':
                        piece = new Knight(c);
                        break;
                    case 'b':
                    case 'B':
                        piece = new Bishop(c);
                        break;
                    case 'q':
                    case 'Q':
                        piece = new Queen(c);
                        break;
                    case 'k':
                    case 'K':
                        piece = new King(c);
                        break;
                    case 'p':
                    case 'P':
                        piece = new Pawn(c);
                        break;
                    default:
                        piece = null;
                        break;
                }
                auxPieceMap.get(c).add(piece);
                piece.setSquare(sq);
                sq.setPiece(piece);
            }
            chessBoard[row][j] = sq;
        }
    }

    public static void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j].getPiece() == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(chessBoard[i][j].getPiece().getType() + " ");
                }
            }
            System.out.println();
        }
    }

    public static void printFen() {
        System.out.println(fen.toString());
    }
}
