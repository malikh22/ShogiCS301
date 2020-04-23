package com.example.shogics301;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

import java.io.Serializable;


/**
 * @author Hera Malik
 */

public class ShogiDropAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 42978563847L;
    private Piece capturedPiece;
    public int newRow, newCol, oldRow, oldCol;
    public Piece thisPiece;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ShogiDropAction(GamePlayer player, Piece piece, int newRow, int newCol, int oldRow, int oldCol) {
        super(player);
        if(piece != null) {
            this.thisPiece = new Piece(piece.getMyBitmap(), piece.getType(), piece.getRow(),
                    piece.getColumn(), piece.getPlayer());
            this.newRow = newRow;
            this.newCol = newCol;
            this.oldRow = oldRow;
            this.oldCol = oldCol;
        }
    }
}
