package com.test.chess;

import com.test.chess.board.ChessBoard;

import java.util.Scanner;

public class ChessPlayApplication {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        ChessBoard.initiate("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        ChessBoard.print();
        String input = scanner.next();
        while (!"exit".equalsIgnoreCase(input)) {
            ChessBoard.move(input);
            ChessBoard.print();
            ChessBoard.printFen();
            input = scanner.next();
        }
    }
}
