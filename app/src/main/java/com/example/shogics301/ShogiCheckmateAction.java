package com.example.shogics301;

import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.actionMessage.GameAction;

/**
 * A game-move object that a shogi player sends to the game to make
 * a move.
 *
 * TODO: update this class to reflect a checkmate.
 *
 * @author Steven R. Vegdahl
 * @version 2 July 2001
 */
public class ShogiCheckmateAction extends GameAction {
    //Tag for logging
    private static final String TAG = "ShogiCheckmateAction";
	private static final long serialVersionUID = -2242980258970485343L;

	// instance variables: the selected row and column
    private int row;
    private int col;

    /**
     * Constructor for ShogiMoveAction
     *
     //@param source the player making the move
     * @param row the row of the square selected (0-2)
     * @param col the column of the square selected
     */
    public ShogiCheckmateAction(GamePlayer player, int row, int col)
    {
        // invoke superclass constructor to set the player
        super(player);

        // set the row and column as passed to us
        this.row = Math.max(0, Math.min(2, row));
        this.col = Math.max(0, Math.min(2, col));
    }

    /**
     * get the object's row
     *
     * @return the row selected
     */
    public int getRow() { return row; }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getCol() { return col; }

}
