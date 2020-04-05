package com.example.shogics301;

public class King extends Piece {

    public King (boolean player)
    {
        super(player);
    }

    @Override
    public boolean canMove(Board board, Spot start,
                           Spot end)
    {
        // we can't move the piece to a spot that has
        // a piece of the same colour
        if (end.getPiece().isplayer() == this.isplayer()) {
            return false;
        }

        return false;
    }
}
}
