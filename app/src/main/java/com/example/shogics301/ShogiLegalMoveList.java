package com.example.shogics301;

import java.io.Serializable;

/**
 * @author Hera Malik
 */

public class ShogiLegalMoveList implements Serializable{
    private static final long serialVersionUID = 42978563847L;
    private int player;
    private int playerIdx;

    public ShogiLegalMoveList(int n){
        this.playerIdx = n;
        if(n == 0){
            this.player = 0;
        } else if (n == 1) {
            this.player = 1;
        }
    }

    public int[][] moves(Piece[][] board, Piece.PieceType pieceName, int currRow, int currCol){
        int[][] moves = new int[20][];
        int[][] dropMoves = new int[81][];

        int row = 0;
        int col = 0;
        int i = 0;

        if(currRow == 0){
            for(row = 6; row > 0; row--){
                for(col = 0; col < 9; col++){
                    if(board[row][col] == null){
                        dropMoves[i] = new int[]{row,col};
                        i++;
                    }
                }
            }
            return dropMoves;
        }

        if(pieceName.equals(Piece.PieceType.PAWN)){
            if (currRow != 0 || currRow != 9) {
                if (player == 0) {
                    if (currRow - 1 >= 0) {
                        if (board[currRow - 1][currCol] == null) {
                            moves[0] = new int[]{currRow - 1, currCol};
                        } else if (board[currRow - 1][currCol] != null && board[currRow - 1][currCol].getPlayer() != player) {
                            moves[0] = new int[]{currRow - 1, currCol};
                        }
                    }
                } else {
                    if (currRow + 1 < 9) {
                        if (board[currRow + 1][currCol] == null) {
                            moves[0] = new int[]{currRow + 1, currCol};
                        } else if (board[currRow + 1][currCol] != null) {
                            if (row + 1 != 7 || row + 1 != 8) {
                                moves[0] = new int[]{currRow + 1, currCol};
                            }
                            if (board[currRow + 1][currCol].getPlayer() != player) {
                                moves[0] = new int[]{currRow + 1, currCol};
                            }
                        }
                    }
                }
            }
        } else if (pieceName.equals(Piece.PieceType.ROOK)) {
            i = 0;

            row = currRow;
            col = currCol - 1;
            while (col >= 0) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                col -= 1;
                i++;
            }

            row = currRow;
            col = currCol + 1;
            while (col < 9) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                col += 1;
                i++;
            }

            row = currRow - 1;
            col = currCol;
            while (row >= 1) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row -= 1;
                i++;
            }

            row = currRow + 1;
            while (row < 9) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row += 1;
                i++;
            }
        } else if (pieceName.equals(Piece.PieceType.LANCE)) {
            col = currCol;
            if(player == 0){
                i = 0;
                row = currRow - 1;
                while(row >= 1){
                    if (board[row][col] != null) {
                        if(board[row][col].getPlayer() != player){
                            moves[i] = new int[]{row, col};
                            i++;
                        }
                        break;
                    }
                    moves[i] = new int[]{row, col};
                    row -= 1;
                    i++;
                }
            } else {
                i = 0;
                row = currRow + 1;
                while (row < 10) {
                    if (board[row][col] != null) {
                        if (board[row][col].getPlayer() != player) {
                            moves[i] = new int[]{row, col};
                            i++;
                        }
                        break;
                    }
                    moves[i] = new int[]{row, col};
                    row += 1;
                    i++;
                }
            }
        } else if (pieceName.equals(Piece.PieceType.BISHOP)) {
            i = 0;

            row = currRow - 1;
            col = currCol - 1;
            while (row >= 1 && col >= 0) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row -= 1;
                col -= 1;
                i++;
            }

            row = currRow + 1;
            col = currCol - 1;
            while (row < 10 && col >= 0) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row += 1;
                col -= 1;
                i++;
            }

            row = currRow - 1;
            col = currCol + 1;
            while (row >= 1 && col < 9) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row -= 1;
                col += 1;
                i++;
            }

            row = currRow + 1;
            col = currCol + 1;
            while (row < 9 && col < 9) {
                if (board[row][col] != null) {
                    if (board[row][col].getPlayer() != player) {
                        moves[i] = new int[]{row, col};
                        i++;
                    }
                    break;
                }
                moves[i] = new int[]{row, col};
                row += 1;
                col += 1;
                i++;
            }
        } else if (pieceName.equals(Piece.PieceType.KNIGHT)) {
            if (player == 0) {
                if (currRow - 2 > 0) {
                    if (currCol - 1 >= 0) {
                        if (board[currRow - 2][currCol - 1] != null) {
                            if (player != board[currRow - 2][currCol - 1].getPlayer()) {
                                moves[0] = new int[]{currRow - 2, currCol - 1};
                            }
                        } else {
                            moves[0] = new int[]{currRow - 2, currCol - 1};
                        }
                    }

                    if (currCol + 1 < 9) {
                        if(board[currRow - 2][currCol + 1] != null){
                            if (player != board[currRow - 2][currCol + 1].getPlayer()) {
                                moves[1] = new int[]{currRow - 2, currCol + 1};
                            }
                        } else {
                            moves[1] = new int[]{currRow - 2, currCol + 1};
                        }
                    }
                }
            } else {
                if (currRow + 2 < 9) {
                    if (currCol - 1 >= 0) {
                        if (board[currRow + 2][currCol - 1] != null) {
                            if (player != board[currRow + 2][currCol - 1].getPlayer()) {
                                moves[0] = new int[]{currRow + 2, currCol - 1};
                            }
                        } else {
                            moves[0] = new int[]{currRow + 2, currCol - 1};
                        }
                    }

                    if (currCol + 1 < 9) {
                        if (board[currRow + 2][currCol + 1] != null) {
                            if (player != board[currRow + 2][currCol + 1].getPlayer()) {
                                moves[1] = new int[]{currRow + 2, currCol + 1};
                            }
                        } else {
                            moves[1] = new int[]{currRow + 2, currCol + 1};
                        }
                    }
                }
            }
        } else if (pieceName.equals(Piece.PieceType.GOLDGENERAL) ||
                pieceName.equals(Piece.PieceType.KING)) {
            i = 0;
            if (player == 0) {
                if (currRow != 1 && currCol != 0) {
                    if (board[currRow - 1][currCol - 1] != null) {
                        if (player != board[currRow - 1][currCol - 1].getPlayer()) {
                            moves[i] = new int[]{currRow - 1, currCol - 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow - 1, currCol - 1};
                    }
                }
                i++;

                if (currRow != 1 && currCol != 8) {
                    if (board[currRow - 1][currCol + 1] != null) {
                        if (player != board[currRow - 1][currCol + 1].getPlayer()) {
                            moves[i] = new int[]{currRow - 1, currCol + 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow - 1, currCol + 1};
                    }
                }
                i++;
            } else {
                if (currRow != 9 && currCol != 0) {
                    if (board[currRow + 1][currCol - 1] != null) {
                        if (player != board[currRow + 1][currCol - 1].getPlayer()) {
                            moves[i] = new int[]{currRow + 1, currCol - 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow + 1, currCol - 1};
                    }
                }
                i++;

                if (currRow != 9 && currCol != 8) {
                    if (board[currRow + 1][currCol + 1] != null) {
                        if (player != board[currRow + 1][currCol + 1].getPlayer()) {
                            moves[i] = new int[]{currRow + 1, currCol + 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow + 1, currCol + 1};
                    }
                }
                i++;
            }

            if (currRow != 1) {
                if (board[currRow - 1][currCol] != null) {
                    if (player != board[currRow - 1][currCol].getPlayer()) {
                        moves[i] = new int[]{currRow - 1, currCol};
                    }
                } else {
                    moves[i] = new int[]{currRow - 1, currCol};
                }
            }
            i++;

            if (!pieceName.equals(Piece.PieceType.SILVERGENERAL)) {
                if (currRow != 9) {
                    if (board[currRow + 1][currCol] != null) {
                        if (player != board[currRow + 1][currCol].getPlayer()) {
                            moves[i] = new int[]{currRow + 1, currCol};
                        }
                    } else {
                        moves[i] = new int[]{currRow + 1, currCol};
                    }
                }
                i++;

                if (currCol != 8) {
                    if (board[currRow][currCol + 1] != null) {
                        if (player != board[currRow][currCol + 1].getPlayer()) {
                            moves[i] = new int[]{currRow, currCol + 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow, currCol + 1};
                    }
                }
                i++;

                if (currCol != 0) {
                    if (board[currRow][currCol - 1] != null) {
                        if (player != board[currRow][currCol - 1].getPlayer()) {
                            moves[i] = new int[]{currRow, currCol - 1};
                        }
                    } else {
                        moves[i] = new int[]{currRow, currCol - 1};
                    }
                }
                i++;
            }

            if (pieceName.equals(Piece.PieceType.KING)) {
                if (player == 0) {
                    if (currRow + 1 < 10 && currCol - 1 > 0) {
                        if (board[currRow + 1][currCol - 1] != null) {
                            if (player != board[currRow + 1][currCol - 1].getPlayer()) {
                                moves[i] = new int[]{currRow + 1, currCol - 1};
                            }
                        } else {
                            moves[i] = new int[]{currRow + 1, currCol - 1};
                        }
                        i++;
                    }
                    if (currRow + 1 < 10 && currCol + 1 < 9) {
                        if (board[currRow + 1][currCol + 1] != null) {
                            if (player != board[currRow + 1][currCol + 1].getPlayer()) {
                                moves[i] = new int[]{currRow + 1, currCol + 1};
                            }
                        } else {
                            moves[i] = new int[]{currRow + 1, currCol + 1};
                        }
                        i++;
                    }
                } else {
                    if (currRow != 1 && currCol != 0 && currRow != 8 && currCol != 8) {
                        if (board[currRow - 1][currCol - 1] != null) {
                            if (player != board[currRow - 1][currCol - 1].getPlayer()) {
                                moves[i] = new int[]{currRow - 1, currCol - 1};
                            }
                        } else {
                            moves[i] = new int[]{currRow - 1, currCol - 1};
                        }
                        i++;

                        if (board[currRow - 1][currCol + 1] != null) {
                            if (player != board[currRow - 1][currCol + 1].getPlayer()) {
                                moves[i] = new int[]{currRow - 1, currCol + 1};
                            }
                        } else {
                            moves[i] = new int[]{currRow - 1, currCol + 1};
                        }
                        i++;
                    }
                }
            }
        } else if (pieceName.equals(Piece.PieceType.SILVERGENERAL)) {
            i = 0;
            if(currRow-1 > 0 && currCol-1 >= 0 &&
                    (board[currRow-1][currCol-1] == null || player != board[currRow-1][currCol-1].getPlayer())){
                moves[i] = new int[]{currRow-1, currCol-1};
                i++;
            }

            if(currRow-1 > 1 && currCol+1 < 9 &&
                    (board[currRow-1][currCol+1] == null || player != board[currRow-1][currCol+1].getPlayer())){
                moves[i] = new int[]{currRow-1, currCol+1};
                i++;
            }

            if(currRow+1 < 10 && currCol+1 > 0 &&
                    (board[currRow+1][currCol+1] == null || player != board[currRow+1][currCol+1].getPlayer())){
                moves[i] = new int[]{currRow+1, currCol+1};
                i++;
            }

            if(currRow+1 < 10 && currCol-1 > 0 &&
                    (board[currRow+1][currCol-1] == null || player != board[currRow+1][currCol-1].getPlayer())){
                moves[i] = new int[]{currRow+1, currCol-1};
                i++;
            }

            if(player == 0){
                if (currRow - 1 > 0 &&
                        (board[currRow - 1][currCol] == null || player != board[currRow - 1][currCol].getPlayer())) {
                    moves[i] = new int[]{currRow - 1, currCol};
                    i++;
                }
            }else{
                if (currRow + 1 < 10 &&
                        (board[currRow + 1][currCol] == null || player != board[currRow + 1][currCol].getPlayer())) {
                    moves[i] = new int[]{currRow + 1, currCol};
                    i++;
                }
            }
        } else {
            for (i = 0; i < 20; i++) {
                moves[i] = new int[]{currRow, currCol};
            }
        }

        if (board[currRow][currCol].getPromotedPiece() != null) {
            if (pieceName.equals(Piece.PieceType.PAWN) || pieceName.equals(Piece.PieceType.SILVERGENERAL)
                    || pieceName.equals(Piece.PieceType.KNIGHT) || pieceName.equals(Piece.PieceType.LANCE)) {
                return moves(board, Piece.PieceType.GOLDGENERAL, currRow, currCol);
            } else if (pieceName.equals(Piece.PieceType.BISHOP)) {
                i = 16;
                if(currRow-1 >= 1) {
                    if (board[currRow - 1][currCol] == null || board[currRow - 1][currCol].getPlayer() != player) {
                        moves[i] = new int[]{currRow - 1, currCol};
                        i++;
                    }
                }
                if(currRow+1 < 10){
                    if(board[currRow+1][currCol] == null || board[currRow+1][currCol].getPlayer() != player){
                        moves[i] = new int[]{currRow+1, currCol};
                        i++;
                    }
                }
                if(currCol-1 >= 0) {
                    if (board[currRow][currCol-1] == null || board[currRow][currCol-1].getPlayer() != player) {
                        moves[i] = new int[]{currRow, currCol-1};
                        i++;
                    }
                }
                if(currCol+1 < 9){
                    if(board[currRow][currCol+1] == null || board[currRow][currCol+1].getPlayer() != player){
                        moves[i] = new int[]{currRow, currCol+1};
                    }
                }
                return moves;
            } else if (pieceName.equals(Piece.PieceType.ROOK)){
                i = 16;
                if(currRow+1 < 9 && currCol+1 < 9){
                    if(board[currRow+1][currCol+1] == null || board[currRow+1][currCol+1].getPlayer() != player){
                        moves[i] = new int[]{currRow+1, currCol+1};
                        i++;
                    }
                }

                if(currRow+1 < 9 && currCol-1 >= 0){
                    if(board[currRow+1][currCol-1] == null || board[currRow+1][currCol-1].getPlayer() != player){
                        moves[i] = new int[]{currRow+1, currCol-1};
                        i++;
                    }
                }

                if(currRow-1 > 1 && currCol+1 < 8){
                    if(board[currRow-1][currCol+1] == null || board[currRow-1][currCol+1].getPlayer() != player){
                        moves[i] = new int[]{currRow-1, currCol+1};
                        i++;
                    }
                }

                if(currRow-1 > 1 && currCol-1 >= 0){
                    if(board[currRow-1][currCol-1] == null || board[currRow-1][currCol-1].getPlayer() != player){
                        moves[i] = new int[]{currRow-1, currCol-1};
                    }
                }

                return moves;
            }
        }

        return moves;
    }

    public int[][] kingInCheck(Piece[][] board, ShogiState state, int currRow, int currCol){
        if(!board[currRow][currCol].getType().equals(Piece.PieceType.KING)){ return null; }

        Piece piece = board[currRow][currCol];
        int[][] possibleMoves;// = moves(board, "King", currRow, currCol);

        if(state.determinePlayerInCheck(playerIdx, board, piece.getRow(), piece.getColumn())){
            possibleMoves = moves(board, piece.getType(), piece.getRow(), piece.getColumn());
            for(int i = 0; i < possibleMoves.length; i++){
                if(possibleMoves[i] == null){ continue; }
                if(state.determinePlayerInCheck(playerIdx, board, possibleMoves[i][0], possibleMoves[i][1])){
                    possibleMoves[i] = null;
                }
            }
        }
        return new int[][]{{4}};
    }
}