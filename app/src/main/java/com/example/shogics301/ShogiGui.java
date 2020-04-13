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

    private Piece humanKingPiece;
    private Piece humanSGPiece;
    private Piece humanGGPiece;
    private Piece humanSGPiece1;
    private Piece humanGGPiece1;
    private Piece humanPawnPiece;
    private Piece humanPawnPiece1;
    private Piece humanPawnPiece2;
    private Piece humanPawnPiece3;
    private Piece humanPawnPiece4;
    private Piece humanPawnPiece5;
    private Piece humanPawnPiece6;
    private Piece humanPawnPiece7;
    private Piece humanPawnPiece8;
    private Piece humanBishopPiece;
    private Piece humanLancePiece;
    private Piece humanRookPiece;
    private Piece humanKnightPiece;
    private Piece humanKnightPiece1;


    private Piece computerKingPiece;
    private Piece computerSGPiece;
    private Piece computerSGPiece1;
    private Piece computerGGPiece;
    private Piece computerGGPiece1;
    private Piece computerPawnPiece;
    private Piece computerPawnPiece1;
    private Piece computerPawnPiece2;
    private Piece computerPawnPiece3;
    private Piece computerPawnPiece4;
    private Piece computerPawnPiece5;
    private Piece computerPawnPiece6;
    private Piece computerPawnPiece7;
    private Piece computerPawnPiece8;
    private Piece computerLancePiece;
    private Piece computerLancePiece1;
    private Piece computerRookPiece;
    private Piece computerKnightPiece;
    private Piece computerKnightPiece1;
    private Piece computerBishopPiece;

    Paint square = new Paint();
    Point size = new Point();
    public static int width, height;

    //constructor
    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), shougi_board);
        board = Bitmap.createScaledBitmap(board, 1400, 1400, false);


        //the following board set up is for the start of the game

        //human piece objects
        humanKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        humanKingPiece = new Piece(humanKing, Piece.PieceType.KING, 8, 4);

        humanSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        humanSGPiece = new Piece(humanSG, Piece.PieceType.SILVERGENERAL, 8, 2);
        humanSGPiece1 = new Piece(humanSG, Piece.PieceType.SILVERGENERAL, 8, 6);

        humanGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        humanGGPiece = new Piece(humanGG, Piece.PieceType.GOLDGENERAL, 8, 3);
        humanGGPiece1 = new Piece(humanGG, Piece.PieceType.GOLDGENERAL, 8, 5);

        humanPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        humanPawnPiece = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 0);
        humanPawnPiece1 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 1);
        humanPawnPiece2 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 2);
        humanPawnPiece3 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 3);
        humanPawnPiece4 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 4);
        humanPawnPiece5 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 5);
        humanPawnPiece6 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 6);
        humanPawnPiece7 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 7);
        humanPawnPiece8 = new Piece(humanPawn, Piece.PieceType.PAWN, 6, 8);

        humanBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        humanBishopPiece = new Piece(humanBishop, Piece.PieceType.BISHOP, 7, 1);

        humanLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        humanLancePiece = new Piece(humanLance, Piece.PieceType.LANCE, 8, 0);

        humanRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        humanRookPiece = new Piece(humanRook, Piece.PieceType.ROOK, 7, 7);

        humanKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        humanKnightPiece = new Piece(humanKnight, Piece.PieceType.KNIGHT, 8, 1);
        humanKnightPiece1 = new Piece(humanKnight, Piece.PieceType.KNIGHT, 8, 7);


        //computer piece objects
        computerKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        computerKingPiece = new Piece(computerKing, Piece.PieceType.KING, 0, 4);

        computerSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        computerSGPiece = new Piece(computerSG, Piece.PieceType.SILVERGENERAL, 0, 2);
        computerSGPiece1 = new Piece(computerSG, Piece.PieceType.SILVERGENERAL, 0, 6);

        computerGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        computerGGPiece = new Piece(computerGG, Piece.PieceType.GOLDGENERAL, 0, 3);
        computerGGPiece1 = new Piece(computerGG, Piece.PieceType.GOLDGENERAL, 0, 5);

        computerPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        computerPawnPiece = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 0);
        computerPawnPiece1 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 1);
        computerPawnPiece2 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 2);
        computerPawnPiece3 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 3);
        computerPawnPiece4 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 4);
        computerPawnPiece5 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 5);
        computerPawnPiece6 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 6);
        computerPawnPiece7 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 7);
        computerPawnPiece8 = new Piece(computerPawn, Piece.PieceType.PAWN, 2, 8);

        computerBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        computerBishopPiece = new Piece(computerBishop, Piece.PieceType.BISHOP, 1, 7);

        computerLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        computerLancePiece = new Piece(computerLance, Piece.PieceType.LANCE, 0, 0);
        computerLancePiece1 = new Piece(computerLance, Piece.PieceType.LANCE, 0, 8);

        computerRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        computerRookPiece = new Piece(computerRook, Piece.PieceType.ROOK, 1, 1);

        computerKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        computerKnightPiece = new Piece(computerKnight, Piece.PieceType.KNIGHT, 0, 1);
        computerKnightPiece1 = new Piece(computerKnight, Piece.PieceType.KNIGHT, 0, 7);


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
        //for iterating and managing the pieces array
        //TODO: complete when we have a way to draw pieces
        int j;
        for (i = 0; i < 11; i++) {
            for (j = 0; j < 9; j++) {
                canvas.drawBitmap(humanKingPiece.getMyBitmap(), humanKingPiece.getRow(), humanKingPiece.getColumn(), null);

            }
        }
    }

}