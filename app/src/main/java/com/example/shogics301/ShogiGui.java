package com.example.shogics301;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Button;

import com.example.shogics301.GameFramework.utilities.FlashSurfaceView;
import com.example.shogics301.GameFramework.utilities.Logger;

import static com.example.shogics301.R.drawable.shougi_board;

/*
 * Draws a Shogi board.
 *
 * TODO: Draw pieces somehow
 */

public class ShogiGui extends FlashSurfaceView {
    public Piece myPieces[][];


    //instance values for board creation
    public static final float space = 125; //150 is height/width of rows & cols
    public static final float topLeftX = 0 + space / 2; //95 is good
    public static final float topLeftY = 200; //350 is good
    public boolean pieceIsSelected;
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
        board = Bitmap.createScaledBitmap(board, 1300, 1400, false);

        myPieces = new ShogiState().getBoard();


        //human piece objects
        humanKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        humanKing = Bitmap.createScaledBitmap(humanKing, 120, 120, false);


        humanSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        humanSG = Bitmap.createScaledBitmap(humanSG, 120, 120, false);

        humanGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        humanGG = Bitmap.createScaledBitmap(humanGG, 120, 120, false);

        humanPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        humanPawn = Bitmap.createScaledBitmap(humanPawn, 120, 120, false);

        humanBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        humanBishop = Bitmap.createScaledBitmap(humanBishop, 120, 120, false);

        humanLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        humanLance = Bitmap.createScaledBitmap(humanLance, 120, 120, false);

        humanRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        humanRook = Bitmap.createScaledBitmap(humanRook, 120, 120, false);

        humanKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        humanKnight = Bitmap.createScaledBitmap(humanKnight, 120, 120, false);

        //computer piece objects
        computerKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        computerKing = Bitmap.createScaledBitmap(computerKing, 120, 120, false);
        computerKing = RotateBitmap(computerKing, 180);

        computerSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        computerSG = Bitmap.createScaledBitmap(computerSG, 120, 120, false);
        computerSG = RotateBitmap(computerSG, 180);

        computerGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        computerGG = Bitmap.createScaledBitmap(computerGG, 120, 120, false);
        computerGG = RotateBitmap(computerGG, 180);

        computerPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        computerPawn = Bitmap.createScaledBitmap(computerPawn, 120, 120, false);
        computerPawn = RotateBitmap(computerPawn, 180);

        computerBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        computerBishop = Bitmap.createScaledBitmap(computerBishop, 120, 120, false);
        computerBishop = RotateBitmap(computerBishop, 180);

        computerLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        computerLance = Bitmap.createScaledBitmap(computerLance, 120, 120, false);
        computerLance = RotateBitmap(computerLance, 180);

        computerRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        computerRook = Bitmap.createScaledBitmap(computerRook, 120, 120, false);
        computerRook = RotateBitmap(computerRook, 180);

        computerKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        computerKnight = Bitmap.createScaledBitmap(computerKnight, 120, 120, false);
        computerKnight = RotateBitmap(computerKnight, 180);

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


        //This draws the pieces from the board

        for (int k = 0; k < 9; k++) {
            for (int l = 0; l < 9; l++) {
                if (myPieces[k][l] != null) {
                    float left = topLeftX + (myPieces[k][l].getColumn() * space);
                    float top = topLeftY + (myPieces[k][l].getRow() * space);
                    //draws opponent pieces
                    if (myPieces[k][l].getType() == Piece.PieceType.PAWN
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerPawn);
                        canvas.drawBitmap(computerPawn, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KNIGHT
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerKnight);
                        canvas.drawBitmap(computerKnight, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.ROOK
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerRook);
                        canvas.drawBitmap(computerRook, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.BISHOP
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerBishop);
                        canvas.drawBitmap(computerBishop, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KING
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerKing);
                        canvas.drawBitmap(computerKing, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.GOLDGENERAL
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerGG);
                        canvas.drawBitmap(computerGG,left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.SILVERGENERAL
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerSG);
                        canvas.drawBitmap(computerSG, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.LANCE
                            && myPieces[k][l].getPlayer() == 1) {
                        myPieces[k][l].setMyBitmap(computerLance);
                        canvas.drawBitmap(computerLance, left, top, null);
                    }


                    //draws player pieces
                    if (myPieces[k][l].getType() == Piece.PieceType.PAWN
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanPawn);
                        canvas.drawBitmap(humanPawn, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KNIGHT
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanKnight);
                        canvas.drawBitmap(humanKnight, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.ROOK
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanRook);
                        canvas.drawBitmap(humanRook, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.BISHOP
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanBishop);
                        canvas.drawBitmap(humanBishop,left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.KING
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanKing);
                        canvas.drawBitmap(humanKing,left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.GOLDGENERAL
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanGG);
                        Logger.log("draw gold general","draw gold general at "+left+","+top);
                        canvas.drawBitmap(humanGG,left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.SILVERGENERAL
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanSG);
                        canvas.drawBitmap(humanSG, left, top, null);
                    }
                    if (myPieces[k][l].getType() == Piece.PieceType.LANCE
                            && myPieces[k][l].getPlayer() == 0) {
                        myPieces[k][l].setMyBitmap(humanLance);
                        canvas.drawBitmap(humanLance, left, top, null);

                    }
                }
            }
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    @Override
    public void flash(int color, int millis)
    {
        super.flash(color, millis);

    }
}