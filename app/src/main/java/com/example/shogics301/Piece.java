package com.example.shogics301;

public abstract class Piece {

    private boolean captured = false;
    private boolean player = false;

    public Piece(boolean player)
    {
        this.setplayer(player);
    }

    public boolean isplayer()
    {
        return this.player == true;
    }

    public void setplayer(boolean player)
    {
        this.player = player;
    }

    public boolean iscaptured()
    {
        return this.captured == true;
    }

    public void setcaptured(boolean captured)
    {
        this.captured = captured;
    }

    public abstract boolean canMove(Board board,
                                    Spot start, Spot end);


}
