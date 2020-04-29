package com.example.shogics301;

import android.graphics.Bitmap;


/**
 * A representation of a Shogi piece
 * @author Hera Malik
 * @author Josh Henderson
 * @version April 2020
 */
public class Piece {

    private boolean captured = false;
    private PieceType myType = null;
    private int row = 300;
    private int column = 300;
    private Bitmap myBitmap;
    private int player;
    private boolean selected = false;

    //get the promoted version of a base piece
    PieceType getPromotedPiece() {
        if (this.getType() == PieceType.PAWN) {
            return PieceType.P_PAWN;
        }
        if (this.getType() == PieceType.KNIGHT) {
            return PieceType.P_KNIGHT;
        }
        if (this.getType() == PieceType.ROOK) {
            return PieceType.P_ROOK;
        }
        if (this.getType() == PieceType.LANCE) {
            return PieceType.P_LANCE;
        }
        if (this.getType() == PieceType.SILVERGENERAL) {
            return PieceType.P_SILVER;
        }
        if (this.getType() == PieceType.BISHOP) {
            return PieceType.P_BISHOP;
        } else return null;
    }


    //check if a given piece is promoted
    boolean isPromoted(Piece piece) {
        return piece.getType() == PieceType.P_BISHOP || piece.getType() == PieceType.P_LANCE
                || piece.getType() == PieceType.P_ROOK ||
                piece.getType() == PieceType.P_PAWN || piece.getType() == PieceType.P_KNIGHT
                || piece.getType() == PieceType.P_SILVER;


    }

    /**
     * don't know what's going on here but don't touch
     */
    boolean legalMove(Piece[][] board, int currRow, int currCol) {
        return true;
    }

    boolean getSelected() {
        return this.selected;
    }


    enum PieceType {
        BISHOP, GOLDGENERAL, KING, KNIGHT, LANCE, PAWN, ROOK, SILVERGENERAL,
        P_BISHOP, P_ROOK, P_SILVER, P_PAWN, P_LANCE, P_KNIGHT
    }

    public Piece(Bitmap bitmap, PieceType type, int row, int column, int player) {
        this.setMyBitmap(bitmap);
        this.setMyType(type);
        this.row = row;
        this.column = column;
        this.player = player;
    }

    public Piece(Bitmap bitmap, PieceType type, int player) { //for captured piece purposes
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

    public PieceType getType() {
        return this.myType;
    }

    void setMyType(PieceType pieceType) {
        this.myType = pieceType;
    }

    public void setRow(int row) {
        this.row = row;
    }

    void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    int getColumn() {
        return this.column;
    }

    void setMyBitmap(Bitmap bitmap) {
        this.myBitmap = bitmap;
    }

    Bitmap getMyBitmap() {
        return myBitmap;
    }

    public int getPlayer() {
        return this.player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    void setSelected(boolean b) {
        this.selected = b;
    }

}
