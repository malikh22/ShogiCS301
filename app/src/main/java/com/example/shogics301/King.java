package com.example.shogics301;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.*;



public class King extends Piece {

    public King (boolean player)
    {
        super(player);
    }

    @Override
    public void drawPiece(int x, int y) {
//
//        king = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
//                R.drawable.king);
//        king = scaleDown(king, 250,true);

    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                maxImageSize / realImage.getWidth(),
                maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());
        return Bitmap.createScaledBitmap(realImage, width,
                height, filter);
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

//
//rock = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
//        R.drawable.rock);
//        rock = scaleDown(rock, 250,true);
//        paper = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
//        R.drawable.paper);
//        paper = scaleDown(paper, 250,true);
//        scissors = BitmapFactory.decodeResource(this.getApplicationContext().getResources(),
//        R.drawable.scissors);
//        scissors = scaleDown(scissors, 250,true);
