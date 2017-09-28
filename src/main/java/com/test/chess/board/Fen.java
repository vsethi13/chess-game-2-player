package com.test.chess.board;

import com.test.chess.piece.Piece;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
public class Fen {

    private String[] rankArray;
    private String sideToMove;
    private String whiteCastleAbility;
    private String blackCastleAbility;
    private String enPassantTargetSquare;
    private Integer halfMoveCounter;
    private Integer fullMoveCounter;

    private static final String SLASH = "/";
    private static final String SPACE = " ";

    Fen(String fenString) {
        rankArray = new String[8];
        setFenString(fenString);
    }

    private void setFenString(String fenString) {
        int i = 0;
        String[] groups = fenString.split(SPACE);
        for (String rs : groups[0].split(SLASH)) {
            rankArray[i++] = rs;
        }
        sideToMove = groups[1];
        whiteCastleAbility = groups[2].substring(0, 2);
        blackCastleAbility = groups[2].substring(2, 4);
        enPassantTargetSquare = groups[3];
        halfMoveCounter = Integer.valueOf(groups[4]);
        fullMoveCounter = Integer.valueOf(groups[5]);
    }

    void regenerateRank(int rowNum, Square[] chessBoardRow) {
        int counter = 0;
        StringBuilder rankStr = new StringBuilder();
        for (Square sq : chessBoardRow) {
            if (counter > 0 && !sq.isVacant()) {
                rankStr.append(counter);
                counter = 0;
            }
            if (sq.isVacant()) {
                counter++;
            } else {
                rankStr.append(sq.getPiece().getType());
            }
        }
        if (counter > 0) {
            rankStr.append(counter);
        }
        rankArray[rowNum] = rankStr.toString();
    }

    void toggleTurnAndIncCounters(boolean isCaptureOrPawnMove) {
        if (sideToMove.equals("w")) {
            sideToMove = "b";
        } else {
            incrementFullMoveCounter();
            sideToMove = "w";
        }
        if (isCaptureOrPawnMove) {
            resetHalfMoveCounter();
        } else {
            incrementHalfMoveCouner();
        }
    }

    void setEnPassantTargetSquare(Piece piece, Square source, Square destination) {
        String result = "-";
        if (piece.getType() == 'p' || piece.getType() == 'P') {
            if (Math.abs(destination.getRow() - source.getRow()) == 2) {
                result = String.valueOf(source.getFile());
                if (Character.isUpperCase(piece.getType())) {
                    result += "3";
                } else {
                    result += "6";
                }
            }
        }
        enPassantTargetSquare = result;
    }

    private void incrementFullMoveCounter() {
        fullMoveCounter++;
    }

    private void incrementHalfMoveCouner() {
        halfMoveCounter++;
    }

    private void resetHalfMoveCounter() {
        halfMoveCounter = 0;
    }

    @Override
    public String toString() {
        return Arrays.stream(rankArray).collect(Collectors.joining(SLASH)) + SPACE +
                sideToMove + SPACE +
                whiteCastleAbility + blackCastleAbility + SPACE +
                enPassantTargetSquare + SPACE +
                halfMoveCounter + SPACE + fullMoveCounter;
    }
}
