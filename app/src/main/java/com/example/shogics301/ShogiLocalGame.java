package com.example.shogics301;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.LocalGame;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

/**
 * The ShogiLocalGame class for a Shogi game.  Defines and enforces
 * the game rules; handles interactions with players.
 *
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

	public ShogiLocalGame(){
		this.gameState = new ShogiState();
		for(int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				boardCopy[i][j] = gameState.getBoard()[i][j];
			}
		}
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
		if(!gameState.getPlayerHasKing(0)){
			return playerNames[1] +" has won!";
		}
		if(!gameState.getPlayerHasKing(1)){
			return playerNames[0] +" has won!";
		}
		return null;
	}

	@Override
	protected boolean makeMove(GameAction action) {
		//Shogi Move Action
		if(action instanceof ShogiMoveAction){
			ShogiMoveAction sma = ((ShogiMoveAction)action);
			Piece[][] newBoard = gameState.getBoard();
			int oldRow = sma.srcRow;
			int oldCol = sma.srcCol;
			int row = sma.destRow;
			int col = sma.destCol;

			if(newBoard[row][col] != null) {
				//if possible, capture the piece at the given spot
				if (gameState.getWhoseMove() == 0 && newBoard[row][col].getPlayer() != 0) {
					if (newBoard[row][col].getType() == Piece.PieceType.KING) {
						gameState.setPlayerHasKing(1);
					} else if (newBoard[row][col].getType() == Piece.PieceType.PAWN) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.PAWN, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.LANCE) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.LANCE, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.KNIGHT) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.LANCE, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.SILVERGENERAL) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.SILVERGENERAL, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.GOLDGENERAL) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.GOLDGENERAL, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.ROOK) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.ROOK, 0));
					} else if (newBoard[row][col].getType() == Piece.PieceType.BISHOP) {
						gameState.capturep0(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.BISHOP, 0));
					}
					newBoard[row][col] = null;
				} else if (gameState.getWhoseMove() == 1 && newBoard[row][col].getPlayer() != 1) {
					if (newBoard[row][col].getType() == Piece.PieceType.KING) {
						gameState.setPlayerHasKing(0);
					} else if (newBoard[row][col].getType() == Piece.PieceType.PAWN) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.PAWN, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.LANCE) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.LANCE, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.KNIGHT) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.LANCE, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.SILVERGENERAL) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.SILVERGENERAL, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.GOLDGENERAL) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.GOLDGENERAL, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.ROOK) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.ROOK, 1));
					} else if (newBoard[row][col].getType() == Piece.PieceType.BISHOP) {
						gameState.capturep1(new Piece(newBoard[row][col].getMyBitmap(), Piece.PieceType.BISHOP, 1));
					}
					newBoard[row][col] = null;
				}

				//Create piece in new spot
				newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(), sma.thisPiece.getType(), row, col, gameState.getWhoseMove());
				newBoard[row][col].setPlayer(sma.thisPiece.getPlayer());
				newBoard[sma.srcRow][sma.srcCol] = null;


				//forced promotion for piece if in proper zone
				if (row < 3 && row >= 0 && sma.thisPiece.getPlayer() == 0) {
					newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(),
							newBoard[row][col].getPromotedPiece(), row, col, 0);
				}

				if (row < 9 && row >= 7 && sma.thisPiece.getPlayer() == 1) {
					newBoard[row][col] = new Piece(sma.thisPiece.getMyBitmap(),
							newBoard[row][col].getPromotedPiece(), row, col, 1);
				}

			}
			else {
				newBoard[row][col] = sma.thisPiece;
				sma.thisPiece.setColumn(col);
				sma.thisPiece.setRow(row);
				newBoard[sma.srcRow][sma.srcCol] = null;
			}

			gameState.setBoard(newBoard);


			if(gameState.getWhoseMove() == 1){ gameState.setWhoseMove(0); }
			else if(gameState.getWhoseMove() == 0){ gameState.setWhoseMove(1); }
			return true;
		}
		return true;
	}
}