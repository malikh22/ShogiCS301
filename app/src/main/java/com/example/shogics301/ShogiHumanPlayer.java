package com.example.shogics301;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.shogics301.GameFramework.GameHumanPlayer;
import com.example.shogics301.GameFramework.GameMainActivity;

import com.example.shogics301.GameFramework.infoMessage.GameInfo;

/**
 * class ShogiHumanPlayer
 * <p>
 * is an abstract base class for a player that is controlled by a human. For any
 * particular game, a subclass should be created that can display the current
 * game state and responds to user commands.
 * <p>
 * TODO: Fix touching
 *
 * @author Steven R. Vegdahl
 * @author Andrew Nuxoll
 * @version July 2013
 */
public class ShogiHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {
    private GameMainActivity myActivity;
    private ShogiState state;
    private Piece[][] myPieces;
    private boolean havePieceSelected = false;
    private int rowSel = 0, colSel = 0;
    private ShogiGui gui;
    private boolean hasKing = true;
    private ShogiGui topView;

    /**
     * constructor
     *
     * @param name the name of the human player
     */
    public ShogiHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return topView;
    }


    /**
     * used to update the human player's game state
     *
     * @param info the updated game state
     */
    @Override
    public void receiveInfo(GameInfo info) {

        //only update the state if info is a game state
        if (info instanceof ShogiState) {

            // update our state, then display
            this.state = (ShogiState) info;
            this.myPieces = state.getBoard();
            gui = (ShogiGui) myActivity.findViewById(R.id.shogiBoard);
            gui.myPieces = this.myPieces;


            gui.invalidate();
        }
    }

    /**
     * @param activity the main activity
     */
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);
        topView = (ShogiGui) myActivity.findViewById(R.id.shogiBoard);
        myActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        topView.setOnTouchListener(this);

        // GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

    /**
     * detects when a button was clicked
     *
     * @param v the view (button) that was clicked
     */
    @Override
    public void onClick(View v) {

    }


    /**
     * this handles a touch on the board so that a move can be made
     *
     * @param v     the view that was touched, i.e. the human player
     * @param event
     * @return true if the listener has detected the event, false otherwise
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("toucheda: ", "first touch ");

        int row, col; //used for storing the location of the space tapped
        if (this.myPieces == null) {
            return false;
        }
        if (event.getAction() != MotionEvent.ACTION_UP) {
            return true;
        }


        //get the row and column of the tapped space
        row = (int) ((event.getY() - ShogiGui.topLeftY) / (ShogiGui.space));
        col = (int) ((event.getX() - ShogiGui.topLeftX) / (ShogiGui.space));
        Log.d("touched", Float.toString(event.getX()));
        Log.d("touched1: ", Integer.toString(row));
        Log.d("touched2: ", Integer.toString(col));


        //dont do anything if the user tapped outside the board
        if (row > 8 || col > 8) {
            Log.d("touchedb: ", "outside board ");

            return false;
        } else if (row < 1 || col < 1) {
            Log.d("touchedb: ", "outside board ");

            return false;
        }
        gui.pieceIsSelected = true;
        havePieceSelected = true;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(gui.myPieces[i][j] != null) {
                    if (gui.myPieces[i][j].getRow() == row && gui.myPieces[i][j].getColumn() == col) {
                        gui.myPieces[row][col].setSelected(true);
                        rowSel = row;
                        colSel = col;
                    }
                }
            }
        }

        //when a piece on the board is currently selected

        if (havePieceSelected) {
            Log.d("touchedc: ", "inside board ");

            //when a piece is currently selected and
            //the tapped space contains one of the player's own pieces
            //old comment: when the user tapped a space that has one of his/her own pieces
            if (state.getWhoseMove() == 0)
                Log.d("touchedD: ", "inside board and player 0");
            {
                if (myPieces[row][col] != null && myPieces[row][col].getPlayer() == 0) {
                    Log.d("touchedD: ", "inside board & player 0 ");

                    //when the player taps the piece that is already selected, deselect it
                    //old comment: This deals with selected and deselecting myPieces
                    if (myPieces[row][col].isSelected()) {
                        Log.d("touchede: ", "already selected ");

                        myPieces[row][col].setSelected(false);
                        gui.pieceIsSelected = false;
                        havePieceSelected = false;
                    }


                    // deselect the currently selected piece and select the other tapped piece
                    else {
                        Log.d("touchedf: ", "deselect ");
                        //find and deselect the currently selected piece
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (myPieces[i][j] != null) {
                                    if (myPieces[i][j].isSelected()) {
                                        myPieces[i][j].setSelected(false);
                                    }
                                }
                            }
                        }
                        Log.d("touchedg: ", "select new piece ");
                        //select the newly tapped piece
                        myPieces[row][col].setSelected(true);
                        gui.pieceIsSelected = true;
                        rowSel = row;
                        colSel = col;
                        Log.d("touched7: ", "select newly tapped piece");

                    }
                }

                if (gui.pieceIsSelected && (myPieces[row][col] == null || myPieces[row][col].getPlayer() == 1)) {
                    Log.d("touched8: ", "move the piece ");
                    // move the piece
                    Log.d("touched8: ", "send the action ");
                    game.sendAction(new ShogiMoveAction(this, myPieces[rowSel][colSel], row, col, rowSel, colSel));
                    Log.d("touched8: ", "sent the action ");
                    //reset
                    havePieceSelected = false;
                    rowSel = -1;
                    colSel = -1;
                }
                else {
                    Log.d("touched9: ", "move the piece ");
                    // move the piece
                    game.sendAction(new ShogiMoveAction(this, myPieces[rowSel][colSel], row, col, rowSel, colSel));
                    Log.d("touched9: ", "move the piece ");
                    //reset
                    havePieceSelected = false;
                    rowSel = 0;
                    colSel = 0;
                }
            }
            //leave everything as it is
            return true;
        }
        //redraw board with myPieces updated
        Log.d("touched4: ", "before invalidate called");
        gui.invalidate();
        Log.d("touched5: ", "after invalidate called");
        //done
        return true;
    }

    public boolean getHasKing() {
        return hasKing;
    }

    public void setHasKing(boolean hasKing) {
        this.hasKing = hasKing;
    }
}