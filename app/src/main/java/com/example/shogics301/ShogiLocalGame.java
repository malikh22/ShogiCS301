package com.example.shogics301;

import android.util.Log;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.LocalGame;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

/**
 * The ShogiLocalGame class for a Shogi game.  Defines and enforces
 * the game rules; handles interactions with players.
 * <p>
 * TODO: Update to reflect Shogi rules and interactions
 *
 * @author Steven R. Vegdahl
 * @author Hera Malik
 * @version April 2020
 */

public class ShogiLocalGame extends LocalGame {
    //Tag for logging

    private static final String TAG = "ShogiLocalGame";

    // the game's state
    private ShogiState gameState;
    public static Piece[][] boardCopy = new Piece[9][9];
    ShogiLegalMoveList legalMove;

    public ShogiLocalGame() {
        this.gameState = new ShogiState();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardCopy[i][j] = gameState.getBoard()[i][j];
            }
        }
        int n = gameState.getWhoseMove();
		legalMove = new ShogiLegalMoveList(n);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //Log.i("p","sent the info");
        p.sendInfo(new ShogiState(gameState));
    }

    @Override
    public boolean canMove(int playerIdx) {

        return playerIdx == gameState.getWhoseMove();
    }

    @Override
    protected String checkIfGameOver() {
        if (!gameState.getPlayerHasKing(0)) {
            return playerNames[1] + " has won!";
        }
        if (!gameState.getPlayerHasKing(1)) {
            return playerNames[0] + " has won!";
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        //Shogi Move Action
        if (action instanceof ShogiMoveAction) {
            ShogiMoveAction sma = ((ShogiMoveAction) action);
            Piece[][] newBoard = gameState.getBoard();
            int oldRow = sma.srcRow;
            int oldCol = sma.srcCol;
            int row = sma.destRow;
            int col = sma.destCol;


            if (legalMove.validMove(newBoard[oldRow][oldCol].getType(), oldRow, oldCol, row, col, newBoard[oldRow][oldCol].getPlayer())) {
                if (newBoard[row][col] != null && newBoard[row][col].getPlayer() != gameState.getWhoseMove()) {
                    //if possible, capture the piece at the given spot
                    if (gameState.getWhoseMove() == 0 && newBoard[row][col].getPlayer() != 0) {
                        if (newBoard[row][col].getType() == Piece.PieceType.KING) {
                            gameState.setPlayerHasKing(1);
                        } else{
                            newBoard[row][col].setPlayer(0);
                            gameState.capturep0(newBoard[row][col]);
                        }
                        newBoard[row][col] = null;
                    } else if (gameState.getWhoseMove() == 1 && newBoard[row][col].getPlayer() != 1) {
                        if (newBoard[row][col].getType() == Piece.PieceType.KING) {
                            gameState.setPlayerHasKing(0);
                        } else  {
                            newBoard[row][col].setPlayer(1);
                            gameState.capturep1(newBoard[row][col]);
                        }
                        newBoard[row][col] = newBoard[oldRow][oldCol];
                    }

                    //Create piece in new spot
                    newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(), sma.thisPiece.getType(), row, col, gameState.getWhoseMove());
                    newBoard[row][col].setPlayer(sma.thisPiece.getPlayer());
                    newBoard[sma.srcRow][sma.srcCol] = null;
                    sma.thisPiece.setColumn(col);
                    sma.thisPiece.setRow(row);


                    //forced promotion for piece if in proper zone
                    if (row < 3 && row >= 0 && sma.thisPiece.getPlayer() == 0) {
                        newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(),
                                newBoard[row][col].getPromotedPiece(), row, col, 0);
                        sma.thisPiece.setColumn(col);
                        sma.thisPiece.setRow(row);
                    }

                    if (row < 9 && row >= 7 && sma.thisPiece.getPlayer() == 1) {
                        newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(),
                                newBoard[row][col].getPromotedPiece(), row, col, 1);
                        sma.thisPiece.setColumn(col);
                        sma.thisPiece.setRow(row);
                    }

                } else {
                    newBoard[row][col] = sma.thisPiece;
                    sma.thisPiece.setColumn(col);
                    sma.thisPiece.setRow(row);
                    newBoard[sma.srcRow][sma.srcCol] = null;
                }

                gameState.setBoard(newBoard);


                if (gameState.getWhoseMove() == 1) {
                    gameState.setWhoseMove(0);
                } else if (gameState.getWhoseMove() == 0) {
                    gameState.setWhoseMove(1);
                }

            } else {
                //figure out how to flash

                return false;

            }
            return true;
        }
        return true;
    }
}