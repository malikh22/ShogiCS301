package com.example.shogics301;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;

import static com.example.shogics301.R.drawable.shougi_board;

/*
 * Draws a Shogi board.
 *
 * TODO: Draw pieces somehow
 */

public class ShogiGui extends SurfaceView {
    public Piece myPieces[][];


    //instance values for board creation
    public static final float space = 150; //150 is height/width of rows & cols
    private Bitmap board;
    private Bitmap humanKing;
    private Bitmap humanSG;
    private Bitmap humanGG;
    private Bitmap humanPawn;
    private Bitmap humanBishop;
    private Bitmap humanLance;
    private Bitmap humanRook;
    private Bitmap humanKnight;
    private Bitmap computerKing;
    private Bitmap computerSG;
    private Bitmap computerGG;
    private Bitmap computerPawn;
    private Bitmap computerLance;
    private Bitmap computerRook;
    private Bitmap computerKnight;
    private Bitmap computerBishop;


    Paint square = new Paint();
    Point size = new Point();
    public static int width, height;

    //constructor
    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), shougi_board);
        board = Bitmap.createScaledBitmap(board, 1400, 1400, false);

        myPieces = new ShogiState().getBoard();


        //human piece objects
        humanKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        humanSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        humanGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        humanPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        humanBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        humanLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        humanRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        humanKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);

        //computer piece objects
        computerKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        computerSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        computerGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        computerPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        computerBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        computerLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        computerRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        computerKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);

    }

    @Override
    public void onDraw(Canvas canvas) {
        //Gets screen size
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        assert wm != null; //checking for NPE
        Display display = wm.getDefaultDisplay();

        //sets size according to display
        display.getSize(size);
        width = (int) (size.x / 1.4);
        height = (int) (size.y / 1.3);

        //creates the colors used
        square.setColor(0xFF000000);
        square.setStyle(Paint.Style.STROKE);
        square.setStrokeWidth(7f);

        //background for the main display
        canvas.drawColor(Color.BLACK);

        //the board
        canvas.drawBitmap(board, 50f, 250f, null);

        //dimensions
        float h = height / (7f);
        float w = width / (16f);

        //draw vertical lines; start xy is top point, end xy is bottom point
        int i;
        for (i = 0; i < 10; i++) {
            canvas.drawLine(w + i * space, h, w + i * space, h + 9 * space, square);
            canvas.drawLine(w, h + i * space, w + 9 * space, h + i * space, square);
        }


        //This draws the pieces from the array
        //TODO: Fix so this actually draws the pieces based on the current board

        for (int k = 0; k < 9; k++) {
            for (int l = 0; l < 9; l++) {
                if (myPieces[k][l] != null) {
                    //draws opponent pieces
                    if (myPieces[k][l].getType() == Piece.PieceType.PAWN
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerPawn);
                        canvas.drawBitmap(computerPawn, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KNIGHT
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerKnight);
                        canvas.drawBitmap(computerKnight, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.ROOK
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerRook);
                        canvas.drawBitmap(computerRook, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.BISHOP
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerBishop);
                        canvas.drawBitmap(computerRook, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KING
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerKing);
                        canvas.drawBitmap(computerKing, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.GOLDGENERAL
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerGG);
                        canvas.drawBitmap(computerGG, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.SILVERGENERAL
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerSG);
                        canvas.drawBitmap(computerSG, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.LANCE
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerLance);
                        canvas.drawBitmap(computerLance, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }


                    //draws player pieces
                    if (myPieces[k][l].getType() == Piece.PieceType.PAWN
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanPawn);
                        canvas.drawBitmap(humanPawn, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KNIGHT
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanKnight);
                        canvas.drawBitmap(humanKnight, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.ROOK
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanRook);
                        canvas.drawBitmap(humanRook, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.BISHOP
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanBishop);
                        canvas.drawBitmap(humanBishop, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KING
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanKing);
                        canvas.drawBitmap(humanKing, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.GOLDGENERAL
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanGG);
                        canvas.drawBitmap(humanGG, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.SILVERGENERAL
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanSG);
                        canvas.drawBitmap(humanSG, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.LANCE
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanLance);
                        canvas.drawBitmap(humanLance, myPieces[k][l].getRow(), myPieces[k][l].getColumn(), null);
                    }
                }
            }
        }
    }
}