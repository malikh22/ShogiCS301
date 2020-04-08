package com.example.shogics301;

public class GoldGeneral extends Piece {

    public GoldGeneral (boolean player)
    {
        super(player);
    }

    @Override
    public boolean canMove()
    {
//        // we can't move the piece to a spot that has
//        // a piece of the same colour
//        if (end.getPiece().isplayer() == this.isplayer()) {
//            return false;
//        }

        return false;
    }
}
