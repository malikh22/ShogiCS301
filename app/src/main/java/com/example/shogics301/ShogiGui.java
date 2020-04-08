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
    private Piece humanPawnPiece;
    private Piece humanBishopPiece;
    private Piece humanLancePiece;
    private Piece humanRookPiece;
    private Piece humanKnightPiece;
    private Piece computerKingPiece;
    private Piece computerSGPiece;
    private Piece computerGGPiece;
    private Piece computerPawnPiece;
    private Piece computerLancePiece;
    private Piece computerRookPiece;
    private Piece computerKnightPiece;
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

        humanKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        humanKingPiece = new Piece(humanKing, Piece.PieceType.KING, 1, 1);

        humanSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        humanSGPiece = new Piece(humanSG, Piece.PieceType.SILVERGENERAL, 1, 2);

        humanGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        humanGGPiece = new Piece(humanGG, Piece.PieceType.GOLDGENERAL, 1, 3);

        humanPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        humanPawnPiece = new Piece(humanPawn, Piece.PieceType.PAWN, 1, 4);

        humanBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        humanBishopPiece = new Piece(humanBishop, Piece.PieceType.BISHOP, 1, 5);

        humanLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        humanLancePiece = new Piece(humanLance, Piece.PieceType.LANCE, 1, 6);


        humanRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        humanRookPiece = new Piece(humanRook, Piece.PieceType.ROOK, 1, 7);

        humanKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        humanKnightPiece = new Piece(humanKnight, Piece.PieceType.KNIGHT, 1, 8);


        computerKing = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        computerKingPiece = new Piece(computerKing, Piece.PieceType.KING, 3, 1);

        computerSG = BitmapFactory.decodeResource(getResources(), R.drawable.silvergeneral);
        computerSGPiece = new Piece(computerSG, Piece.PieceType.SILVERGENERAL, 3, 2);

        computerGG = BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        computerGGPiece = new Piece(computerGG, Piece.PieceType.GOLDGENERAL, 3, 3);

        computerPawn = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        computerPawnPiece = new Piece(computerPawn, Piece.PieceType.PAWN, 3, 4);

        computerBishop = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        computerBishopPiece = new Piece(computerBishop, Piece.PieceType.BISHOP, 3, 5);

        computerLance = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        computerLancePiece = new Piece(computerLance, Piece.PieceType.LANCE, 3, 6);

        computerRook = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        computerRookPiece = new Piece(computerRook, Piece.PieceType.ROOK, 3, 7);

        computerKnight = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        computerKnightPiece = new Piece(computerKnight, Piece.PieceType.KNIGHT, 3, 8);

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
                canvas.drawBitmap(humanBishopPiece.getMyBitmap(), humanBishopPiece.getRow(), humanBishopPiece.getColumn(), null);

            }
        }
    }

}