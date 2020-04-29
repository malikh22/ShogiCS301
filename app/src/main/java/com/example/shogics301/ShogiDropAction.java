package com.example.shogics301;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

import java.io.Serializable;


/**
 * A game-move object that a Shogi player sends to the game to drop
 * a captured piece.
 * @author Hera Malik
 */

public class ShogiDropAction extends GameAction implements Serializable {
    private static final long serialVersionUID = 42978563847L;
    public int newRow, newCol;
    public Piece thisPiece;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ShogiDropAction(GamePlayer player, Piece piece, int newRow, int newCol) {
        super(player);
        if(piece != null) {
            this.thisPiece = new Piece(piece.getMyBitmap(), piece.getType(), newRow,
                    newCol, piece.getPlayer());
            this.newRow = newRow;
            this.newCol = newCol;
        }
    }
}
