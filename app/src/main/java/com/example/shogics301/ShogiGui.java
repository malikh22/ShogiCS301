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
    Paint square = new Paint();
    Point size = new Point();
    public static int width, height;

    //constructor
    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        board = BitmapFactory.decodeResource(getResources(), shougi_board);
        board = Bitmap.createScaledBitmap(board, 1400, 1400, false);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Gets screen size
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        assert wm != null; //checking for NPE
        Display display = wm.getDefaultDisplay();

        //sets size according to display
        display.getSize(size);
        width = (int) (size.x / 1.3);
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
        float h = height/(7f);
        float w = width/(16f);

        //draw vertical lines; start xy is top point, end xy is bottom point
        int i;
        for(i = 0; i < 10; i++) {
            canvas.drawLine(w + i * space, h, w + i * space, h + 9 * space, square);
            canvas.drawLine(w, h + i * space, w + 9 * space, h + i * space, square);
        }


        //This draws the pieces from the array
        //for iterating and managing the pieces array
        //TODO: complete when we have a way to draw pieces
        int j;
        for(i = 0; i < 11; i++) {
            for(j = 0; j < 9; j++){
                //
            }
        }
    }
}