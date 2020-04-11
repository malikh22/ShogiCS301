package com.example.shogics301;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Piece {

    private boolean captured = false;
    private PieceType myType = null;
    private int row = 300;
    private int column = 300;
    private Bitmap myBitmap;
    private int player = 0;
    private boolean selected = false;


    PieceType getPromotedPiece() {
        if (this.getType() == PieceType.PAWN){
            return PieceType.P_PAWN;
        }
        if (this.getType() == PieceType.KNIGHT){
            return PieceType.P_KNIGHT;
        }
        if (this.getType() == PieceType.ROOK){
            return PieceType.P_ROOK;
        }
        if (this.getType() == PieceType.LANCE){
            return PieceType.P_LANCE;
        }
        if(this.getType() == PieceType.SILVERGENERAL){
            return PieceType.P_SILVER;
        }
        if(this.getType() == PieceType.BISHOP){
            return PieceType.P_BISHOP;
        }
        else return null;
    }


    enum PieceType {
        BISHOP, GOLDGENERAL, KING, KNIGHT, LANCE, PAWN, ROOK, SILVERGENERAL,
        P_BISHOP, P_ROOK, P_SILVER, P_PAWN, P_LANCE, P_KNIGHT
    }

    Piece(Bitmap bitmap, PieceType type, int row, int column, int player) {
        this.setMyBitmap(bitmap);
        this.setMyType(type);
        this.row = row;
        this.column = column;
        this.player = player;
    }

    Piece(Bitmap bitmap, PieceType type, int player) { //for captured piece purposes
        this.setMyBitmap(bitmap);
        this.setMyType(type);
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

    public boolean isSelected() { return selected; }

    public void setSelected(boolean b) { this.selected = b;}

}
