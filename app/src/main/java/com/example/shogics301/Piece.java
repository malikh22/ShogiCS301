package com.example.shogics301;

public class Piece {

    private boolean captured = false;
    private boolean player = false;
    private PieceType myType = null;
    enum PieceType {
        BISHOP, GOLDGENERAL, KING, KNIGHT, LANCE, PAWN, ROOK, SILVERGENERAL;
    }

    public Piece(boolean player) {
        this.setplayer(player);
    }

    public boolean isplayer() {
        return this.player == true;
    }

    public void setplayer(boolean player) {
        this.player = player;
    }

    public boolean iscaptured() {
        return this.captured == true;
    }

    public void setcaptured(boolean captured) {
        this.captured = captured;
    }

    public void setMyType(PieceType pieceType)
    {
        this.myType = pieceType;
    }


//    public drawPiece() {
//
//        switch (shogistate.getPiece()) {
//            case 1:
//                piece.setImageResource(R.drawable.bishop);
//                break;
//            case 2:
//                piece.setImageResource(R.drawable.goldgeneral);
//                break;
//            case 3:
//                piece.setImageResource(R.drawable.king);
//                break;
//            case 4:
//                piece.setImageResource(R.drawable.knight);
//                break;
//            case 5:
//                piece.setImageResource(R.drawable.lance);
//                break;
//            case 6:
//                piece.setImageResource(R.drawable.pawn);
//                break;
//            case 7:
//                piece.setImageResource(R.drawable.rook);
//                break;
//            case 8:
//                piece.setImageResource(R.drawable.silvergeneral);
//                break;
//
//
//        }
    }

