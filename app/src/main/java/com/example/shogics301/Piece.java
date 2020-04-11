package com.example.shogics301;

import android.graphics.Bitmap;

public class Piece {

    private boolean captured = false;
    private PieceType myType = null;
    private int row = 300;
    private int column = 300;
    private Bitmap myBitmap;
    private int player = 0;

    enum PieceType {
        BISHOP, GOLDGENERAL, KING, KNIGHT, LANCE, PAWN, ROOK, SILVERGENERAL;
    }

    Piece(Bitmap bitmap, PieceType type, int row, int column, int player) {
        this.setMyBitmap(bitmap);
        this.setMyType(type);
        this.row = row;
        this.column = column;
        this.player = player;
    }


    public boolean isCaptured() {
        return this.captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public PieceType getType() { return this.myType; }

    public void setMyType(PieceType pieceType) {
        this.myType = pieceType;
    }

    public void setRow(int row) { this.row = row; }

    public void setColumn(int column) { this.column = column; }

    public int getRow() { return this.row; }

    public int getColumn() { return this.column; }

    public void setMyBitmap(Bitmap bitmap) { this.myBitmap = bitmap; }

    public Bitmap getMyBitmap() { return myBitmap; }

    public int getPlayer() { return this.player; }

    public void setPlayer(int player) { this.player = player; }
}
