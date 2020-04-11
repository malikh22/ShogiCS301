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
    private Piece humanLancePiece1;
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
        humanLancePiece1 = new Piece(humanLance, Piece.PieceType.LANCE, 8, 8);

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
        canvas.drawBitmap(humanKingPiece.getMyBitmap(), humanKingPiece.getRow(), humanKingPiece.getColumn(), null);

        canvas.drawBitmap(humanSGPiece.getMyBitmap(), humanSGPiece.getRow(), humanSGPiece.getColumn(), null);
        canvas.drawBitmap(humanSGPiece1.getMyBitmap(), humanSGPiece1.getRow(), humanSGPiece1.getColumn(), null);

        canvas.drawBitmap(humanGGPiece.getMyBitmap(), humanGGPiece.getRow(), humanGGPiece.getColumn(), null);
        canvas.drawBitmap(humanGGPiece1.getMyBitmap(), humanSGPiece1.getRow(), humanSGPiece1.getColumn(), null);

        canvas.drawBitmap(humanPawnPiece.getMyBitmap(), humanPawnPiece.getRow(), humanPawnPiece.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece1.getMyBitmap(), humanPawnPiece1.getRow(), humanPawnPiece1.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece2.getMyBitmap(), humanPawnPiece2.getRow(), humanPawnPiece2.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece3.getMyBitmap(), humanPawnPiece3.getRow(), humanPawnPiece3.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece4.getMyBitmap(), humanPawnPiece4.getRow(), humanPawnPiece4.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece5.getMyBitmap(), humanPawnPiece5.getRow(), humanPawnPiece5.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece6.getMyBitmap(), humanPawnPiece6.getRow(), humanPawnPiece6.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece7.getMyBitmap(), humanPawnPiece7.getRow(), humanPawnPiece7.getColumn(), null);
        canvas.drawBitmap(humanPawnPiece8.getMyBitmap(), humanPawnPiece8.getRow(), humanPawnPiece8.getColumn(), null);

        canvas.drawBitmap(humanBishopPiece.getMyBitmap(), humanBishopPiece.getRow(), humanBishopPiece.getColumn(), null);

        canvas.drawBitmap(humanLancePiece.getMyBitmap(), humanLancePiece.getRow(), humanLancePiece.getColumn(), null);
        canvas.drawBitmap(humanLancePiece1.getMyBitmap(), humanLancePiece1.getRow(), humanLancePiece1.getColumn(), null);

        canvas.drawBitmap(humanRookPiece.getMyBitmap(), humanRookPiece.getRow(), humanRookPiece.getColumn(), null);

        canvas.drawBitmap(humanKnightPiece.getMyBitmap(), humanKnightPiece.getRow(), humanKnightPiece.getColumn(), null);
        canvas.drawBitmap(humanKnightPiece1.getMyBitmap(), humanKnightPiece1.getRow(), humanKnightPiece1.getColumn(), null);


        canvas.drawBitmap(computerKingPiece.getMyBitmap(), computerKingPiece.getRow(), computerKingPiece.getColumn(), null);

        canvas.drawBitmap(computerSGPiece.getMyBitmap(), computerSGPiece.getRow(), computerSGPiece.getColumn(), null);
        canvas.drawBitmap(computerSGPiece1.getMyBitmap(), computerSGPiece1.getRow(), computerSGPiece1.getColumn(), null);

        canvas.drawBitmap(computerGGPiece.getMyBitmap(), computerGGPiece.getRow(), computerGGPiece.getColumn(), null);
        canvas.drawBitmap(computerGGPiece1.getMyBitmap(), computerSGPiece1.getRow(), computerSGPiece1.getColumn(), null);

        canvas.drawBitmap(computerPawnPiece.getMyBitmap(), computerPawnPiece.getRow(), computerPawnPiece.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece1.getMyBitmap(), computerPawnPiece1.getRow(), computerPawnPiece1.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece2.getMyBitmap(), computerPawnPiece2.getRow(), computerPawnPiece2.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece3.getMyBitmap(), computerPawnPiece3.getRow(), computerPawnPiece3.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece4.getMyBitmap(), computerPawnPiece4.getRow(), computerPawnPiece4.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece5.getMyBitmap(), computerPawnPiece5.getRow(), computerPawnPiece5.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece6.getMyBitmap(), computerPawnPiece6.getRow(), computerPawnPiece6.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece7.getMyBitmap(), computerPawnPiece7.getRow(), computerPawnPiece7.getColumn(), null);
        canvas.drawBitmap(computerPawnPiece8.getMyBitmap(), computerPawnPiece8.getRow(), computerPawnPiece8.getColumn(), null);

        canvas.drawBitmap(computerBishopPiece.getMyBitmap(), computerBishopPiece.getRow(), computerBishopPiece.getColumn(), null);

        canvas.drawBitmap(computerLancePiece.getMyBitmap(), computerLancePiece.getRow(), computerLancePiece.getColumn(), null);
        canvas.drawBitmap(computerLancePiece1.getMyBitmap(), computerLancePiece1.getRow(), computerLancePiece1.getColumn(), null);

        canvas.drawBitmap(computerRookPiece.getMyBitmap(), computerRookPiece.getRow(), computerRookPiece.getColumn(), null);

        canvas.drawBitmap(computerKnightPiece.getMyBitmap(), computerKnightPiece.getRow(), computerKnightPiece.getColumn(), null);
        canvas.drawBitmap(computerKnightPiece1.getMyBitmap(), computerKnightPiece1.getRow(), computerKnightPiece1.getColumn(), null);

    }

}