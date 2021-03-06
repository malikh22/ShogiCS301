package com.example.shogics301;

import java.io.Serializable;

/**
 * Handles move legality
 * @author Hera Malik
 * @author Josh Henderson
 */

class ShogiLegalMoveList implements Serializable {
    private static final long serialVersionUID = 42978563847L;
    private int player;
    private int playerIdx;

    ShogiLegalMoveList(int n) {
        this.playerIdx = n;
        if (n == 0) {
            this.player = 0;
        } else if (n == 1) {
            this.player = 1;
        }
    }

    int[][] moves(Piece[][] board, Piece.PieceType pieceName, int currRow, int currCol) {
        int[][] moves = new int[20][];
        int[][] dropMoves = new int[81][];

        int row = 0;
        int col = 0;
        int i = 0;

        if (currRow == 0) {
            for (row = 6; row > 0; row--) {
                for (col = 0; col < 9; col++) {
                    if (board[row][col] == null) {
                        dropMoves[i] = new int[]{row, col};
                        i++;
                    }
                }
            }
            return dropMoves;
        }

        if (pieceName.equals(Piece.PieceType.PAWN)) {
            if (player == 0) {
                if (currRow - 1 >= 0) {
                    if (board[currRow - 1][currCol] == null) {
                        moves[0] = new int[]{currRow - 1, currCol};
                    } else if (board[currRow - 1][currCol] != null
                            && board[currRow - 1][currCol].getPlayer() != player) {
                        moves[0] = new int[]{currRow - 1, currCol};
                    }
                }
            } else {
                if (currRow + 1 < 9) {
                    if (board[currRow + 1][currCol] == null) {
                        moves[0] = new int[]{currRow + 1, currCol};
                    } else if (board[currRow + 1][currCol] != null) {
                        moves[0] = new int[]{currRow + 1, currCol};
                        if (board[currRow + 1][currCol].getPlayer() != player) {
                            moves[0] = new int[]{currRow + 1, currCol};
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
            if (player == 0) {
                i = 0;
                row = currRow - 1;
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
                        if (board[currRow - 2][currCol + 1] != null) {
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
            if (currRow - 1 > 0 && currCol - 1 >= 0 &&
                    (board[currRow - 1][currCol - 1] == null || player != board[currRow - 1][currCol - 1].getPlayer())) {
                moves[i] = new int[]{currRow - 1, currCol - 1};
                i++;
            }

            if (currRow - 1 > 1 && currCol + 1 < 9 &&
                    (board[currRow - 1][currCol + 1] == null || player != board[currRow - 1][currCol + 1].getPlayer())) {
                moves[i] = new int[]{currRow - 1, currCol + 1};
                i++;
            }

            if (currRow + 1 < 10 && currCol + 1 > 0 &&
                    (board[currRow + 1][currCol + 1] == null || player != board[currRow + 1][currCol + 1].getPlayer())) {
                moves[i] = new int[]{currRow + 1, currCol + 1};
                i++;
            }

            if (currRow + 1 < 10 && currCol - 1 > 0 &&
                    (board[currRow + 1][currCol - 1] == null || player != board[currRow + 1][currCol - 1].getPlayer())) {
                moves[i] = new int[]{currRow + 1, currCol - 1};
                i++;
            }

            if (player == 0) {
                if (currRow - 1 > 0 &&
                        (board[currRow - 1][currCol] == null || player != board[currRow - 1][currCol].getPlayer())) {
                    moves[i] = new int[]{currRow - 1, currCol};
                    i++;
                }
            } else {
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
                if (currRow - 1 >= 1) {
                    if (board[currRow - 1][currCol] == null || board[currRow - 1][currCol].getPlayer() != player) {
                        moves[i] = new int[]{currRow - 1, currCol};
                        i++;
                    }
                }
                if (currRow + 1 < 10) {
                    if (board[currRow + 1][currCol] == null || board[currRow + 1][currCol].getPlayer() != player) {
                        moves[i] = new int[]{currRow + 1, currCol};
                        i++;
                    }
                }
                if (currCol - 1 >= 0) {
                    if (board[currRow][currCol - 1] == null || board[currRow][currCol - 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow, currCol - 1};
                        i++;
                    }
                }
                if (currCol + 1 < 10) {
                    if (board[currRow][currCol + 1] == null || board[currRow][currCol + 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow, currCol + 1};
                    }
                }
                return moves;
            } else if (pieceName.equals(Piece.PieceType.ROOK)) {
                i = 16;
                if (currRow + 1 < 10 && currCol + 1 < 10) {
                    if (board[currRow + 1][currCol + 1] == null || board[currRow + 1][currCol + 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow + 1, currCol + 1};
                        i++;
                    }
                }

                if (currRow + 1 < 10 && currCol - 1 >= 0) {
                    if (board[currRow + 1][currCol - 1] == null || board[currRow + 1][currCol - 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow + 1, currCol - 1};
                        i++;
                    }
                }

                if (currRow - 1 > 1 && currCol + 1 < 8) {
                    if (board[currRow - 1][currCol + 1] == null || board[currRow - 1][currCol + 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow - 1, currCol + 1};
                        i++;
                    }
                }

                if (currRow - 1 > 1 && currCol - 1 >= 0) {
                    if (board[currRow - 1][currCol - 1] == null || board[currRow - 1][currCol - 1].getPlayer() != player) {
                        moves[i] = new int[]{currRow - 1, currCol - 1};
                    }
                }

                return moves;
            }
        }

        return moves;
    }

    public boolean validMove(Piece[][] board, Piece.PieceType pieceName, int currRow, int currCol, int destRow, int destCol, int player) {
        if (board[destRow][destCol] != null && player == board[destRow][destCol].getPlayer()) {
            return false;
        }

        //king, promoted bishops, promoted rook
        if ((pieceName == Piece.PieceType.KING) || (pieceName == Piece.PieceType.P_BISHOP) || (pieceName == Piece.PieceType.P_ROOK)) {
            boolean d = (((destRow - currRow) * (destRow - currRow)) == 1) && ((((destCol - (currCol)) * ((destCol - (currCol))))) == 1);
            boolean e = ((destRow == currRow) && ((destCol == currCol + 1) || (destCol == currCol - 1))) || ((destCol == currCol) && ((destRow == currRow + 1) || (destRow == currRow - 1)));

            if (d || e) {
                return true;
            }
        }

        //pawns
        if (pieceName == Piece.PieceType.PAWN) {
            if (player == 0) {
                return (destRow == currRow - 1) && (destCol == currCol);
            } else if (player == 1) {
                return (destRow == currRow + 1) && (destCol == currCol);

            }
            return false;
        }

        //rooks and promoted rooks
        if ((pieceName == Piece.PieceType.ROOK) || (pieceName == Piece.PieceType.P_ROOK)) {

            boolean tempRook = false;

            if ((destRow == currRow)) {
                if (destCol > currCol) {
                    for (int i = currCol; i < destCol-1; i++) {
                        if (board[currRow][i + 1] != null) {
                            tempRook = true;
                        }

                        if (tempRook) {
                            return false;
                        }
                    }
                } else if (destCol < currCol) {
                    for (int i = currCol; i > destCol+1; i--) {
                        if (board[currRow][i - 1] != null) {
                            tempRook = true;
                        }
                        if (tempRook) {
                            return false;
                        }
                    }
                }
                return true;

            } else if (destCol == currCol) {
                if (destRow > currRow) {
                    for (int i = currRow; i < destRow-1; i++) {
                        if (board[i + 1][destCol] != null) {
                            tempRook = true;
                        }
                        if (tempRook) {
                            return false;
                        }
                    }
                } else {
                    for (int i = currRow; i > destRow+1; i--) {
                        if (board[i - 1][destCol] != null) {
                            tempRook = true;
                        }
                        if (tempRook) {
                            return false;
                        }
                    }

                }

                return true;
            } else return false;
        }

        //knights
        if (pieceName == Piece.PieceType.KNIGHT) {
            if (player == 0) {
                if ((destRow == currRow - 2) && (((destCol == (currCol + 1))) || (destCol == (currCol - 1)))) {
                    return true;
                }
            } else if (player == 1) {
                if ((destRow == currRow + 2) && (((destCol == (currCol + 1))) || (destCol == (currCol - 1)))) {
                    return true;
                }
            } else
                return false;
        }

        //bishops and promoted bishops
        if ((pieceName == Piece.PieceType.BISHOP) || (pieceName == Piece.PieceType.P_BISHOP)) {
            if (((destRow - currRow) * (destRow - currRow)) == (((destCol - (currCol)) * ((destCol - (currCol)))))) {
                boolean tempBishop = false;

                if ((destRow > currRow)) {
                    int loops =0;
                    if (destCol > currCol) {
                        for (int j = currRow; j < destRow-1; j++) {
                            for (int i = currCol; i < destCol-1; i++) {
                                if (board[j + 1][i + 1 + loops] != null) {
                                    tempBishop = true;
                                }
                                if(!tempBishop) {
                                    break;
                                }
                                return false;
                            }
                            loops++;
                        }
                        return !tempBishop;
                    }
                } else if (destRow < currRow) {
                    int loops = 0;
                    if (destCol < currCol) {
                        for (int j = currRow; j > destRow+1; j--) {
                            for (int i = currCol; i > destCol+1; i--) {
                                if (board[j - 1][i - 1 +loops] != null) {
                                    tempBishop = true;
                                }
                                if(!tempBishop)
                                {
                                    break;
                                }
                                return false;
                            }
                            loops--;
                        }
                        return true;
                    }
                }
                if ((destRow < currRow)) {
                    if (destCol > currCol) {
                        int loops = 0;
                        for (int j = currRow; j > destRow+1; j--) {
                            for (int i = currCol; i < destCol-1; i++) {
                                if (board[j - 1][i + 1+loops] != null) {
                                    tempBishop = true;
                                }
                                if (!tempBishop) {
                                    break;
                                }
                                return false;
                            }
                            loops++;
                        }
                        return !tempBishop;
                    }
                } else if (destRow > currRow) {
                    if (destCol < currCol) {
                        int loops = 0;
                        for (int j = currRow; j < destRow-1; j++) {
                            for (int i = currCol; i > destCol+1; i--) {
                                if (board[j + 1][i - 1+loops] != null) {
                                    tempBishop = true;
                                }
                                if(!tempBishop)
                                {
                                    break;
                                }
                                return false;
                            }
                            loops--;
                        }
                        return !tempBishop;
                    }
                }

            } else return false;
        }


        //lance
        if (pieceName == Piece.PieceType.LANCE) {
            boolean tempRook = false;
            if (destCol == currCol) {
                if (destRow > currRow) {
                    for (int i = currRow; i < destRow-1; i++) {
                        if (!tempRook) {
                            if (board[i + 1][destCol] != null) {
                                tempRook = true;
                            }
                        }
                        if (tempRook) {
                            return false;
                        }
                    }
                } else if (destRow < currRow) {
                    for (int i = currRow; i > destRow+1; i--) {
                        if (!tempRook) {
                            if (board[i - 1][destCol] != null) {
                                tempRook = true;
                            }
                        }
                        if (tempRook) {
                            return false;
                        }
                    }

                }
                if (!tempRook) {
                    return true;
                }
            } else return false;
        }


        //silver general
        if (pieceName == Piece.PieceType.SILVERGENERAL) {
            boolean b = (((destRow - currRow) * (destRow - currRow)) == 1) && ((((destCol - (currCol)) * ((destCol - (currCol))))) == 1);
            if (player == 0) {
                if (b || ((destRow == currRow - 1) && (destCol == currCol))) {
                    return true;
                }
            } else if (player == 1) {
                if (b || ((destRow == currRow + 1) && (destCol == currCol))) {
                    return true;
                }

            } else
                return false;

        }

        //gold generals, promoted silver generals, promoted lances, promoted pawns, promoted pawns
        if ((pieceName == Piece.PieceType.GOLDGENERAL) ^ (pieceName == Piece.PieceType.P_SILVER) ^ (pieceName == Piece.PieceType.P_LANCE) ^ (pieceName == Piece.PieceType.P_KNIGHT) ^ (pieceName == Piece.PieceType.P_PAWN)) {
            boolean c = ((destRow == currRow) && ((destCol == currCol + 1) || (destCol == currCol - 1))) || ((destCol == currCol) && ((destRow == currRow + 1) || (destRow == currRow - 1)));
            if (player == 0) {
                return ((((destRow - currRow) == -1) && (((destCol - currCol) == 1) || ((destCol - currCol) == -1)))) ^ c;
            } else if (player == 1) {
                return ((((destRow - currRow) == 1) && (((destCol - currCol) == 1) || ((destCol - currCol) == -1)))) ^ c;
            } else
                return false;

        }
        return false;

    }
}
