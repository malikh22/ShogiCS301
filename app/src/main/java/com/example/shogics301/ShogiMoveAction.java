package com.example.shogics301;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

import java.io.Serializable;

/**
 * A game-move object that a Shogi player sends to the game to make
 * a move.
 * 
 * @author Steven R. Vegdahl
 * @author Hera Malik
 * @version March 2020
 */
public class ShogiMoveAction extends GameAction implements Serializable {
    //Tag for logging
    private static final String TAG = "ShogiMoveAction";
	private static final long serialVersionUID = -2242980258970485343L;
	
	// instance variables: the selected row and column
    private Piece capturedPiece;
    public int destRow, destCol, srcRow, srcCol;
    public Piece thisPiece;
    public Piece[][] board = new Piece[9][9];

    /**
     * Constructor for ShogiMoveAction
     /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     * @param piece the piece to be moved
     * @param destRow the new row to which shogiPiece Piece will be moved
     * @param destCol the new column to which shogiPiece Piece will be moved
     */
    public ShogiMoveAction(GamePlayer player, Piece piece, int destRow,
                           int destCol, int srcRow, int srcCol) {
        super(player);
        this.thisPiece = new Piece(piece.getMyBitmap(), piece.getType(), piece.getRow(),
                piece.getColumn(), piece.getPlayer());
        this.thisPiece.setPlayer(piece.getPlayer());

        this.destRow = destRow;
        this.destCol = destCol;
        this.srcRow = srcRow;
        this.srcCol = srcCol;
    }

    public ShogiMoveAction(GamePlayer player, Piece[][] board){
        super(player);
        this.board = board;
    }

    int getRow() {
        return destRow;
    }

    int getCol() {
        return destCol;
    }
}

