package com.example.shogics301;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


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
 * TODO: Fix NPEs
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
     * handles touch on the board so that a move can be made
     *
     * @param v     the view that was touched, the human player
     * @param event
     * @return true if the listener detected event, false otherwise
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int row, col; //used for storing the location of the space that the user tapped

        //don't do anything if we don't have the current piece placements
        if (this.myPieces == null) {
            return false;
        }

        //Don't do anything when dragging or lifting touch
        if (event.getAction() != MotionEvent.ACTION_UP) {
            return true;
        }

        //get the row and column of the tapped space
        row = (int) ((event.getY() - ShogiGui.topLeftY) / (ShogiGui.space));
        col = (int) ((event.getX() - ShogiGui.topLeftX) / (ShogiGui.space));
        System.out.print("col: " + col + " row: " + row);


        //don't do anything if the user tapped outside the board
        if (row >= 9 || col >= 9) {
            return false;
        } else if (row < 0 || col < 0) {
            return false;
        }

        //when a piece on the board is currently selected
        if (havePieceSelected) {

            if (state.getWhoseMove() == 0) {
                if (myPieces[row][col] != null && myPieces[row][col].getPlayer() == 0) {

                    if (myPieces[row][col].getSelected()) {
                        myPieces[row][col].setSelected(false);
                        gui.pieceIsSelected = false;
                        havePieceSelected = false;
                    } else {

                        //find and deselect the currently selected piece
                        for (int i = 1; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (myPieces[i][j] != null) {
                                    if (myPieces[i][j].getSelected()) {
                                        myPieces[i][j].setSelected(false);
                                    }
                                }
                            }
                        }

                        //select the newly tapped piece
                        myPieces[row][col].setSelected(true);
                        gui.pieceIsSelected = true;
                        rowSel = row;
                        colSel = col;
                    }
                }

                //check if the tapped space is a legal move for the currently selected piece.
                // If it is, move the piece
                else if (myPieces[rowSel][colSel].legalMove(myPieces, row, col)) {

                    game.sendAction(new ShogiMoveAction(this, myPieces[rowSel][colSel], row, col, rowSel, colSel));

                    //reset
                    havePieceSelected = false;
                    rowSel = 0;
                    colSel = 0;
                } else return true;

            } else {
                if (myPieces[row][col] != null && myPieces[row][col].getPlayer() == 1) {

                    if (myPieces[row][col].getSelected()) {
                        myPieces[row][col].setSelected(false);
                        gui.pieceIsSelected = false;
                        havePieceSelected = false;
                    }

                    //select the other tapped piece
                    else {

                        //find and deselect the currently selected piece
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (myPieces[i][j] != null) {
                                    if (myPieces[i][j].getSelected()) {
                                        myPieces[i][j].setSelected(false);
                                    }
                                }
                            }
                        }

                        //select the newly tapped piece
                        myPieces[row][col].setSelected(true);
                        gui.pieceIsSelected = true;
                        rowSel = row;
                        colSel = col;
                    }
                }


                //move the piece
                else if (myPieces[rowSel][colSel].legalMove(myPieces, row, col)) {
                    game.sendAction(new ShogiMoveAction(this, myPieces[rowSel][colSel], row, col, rowSel, colSel));

                    //reset
                    havePieceSelected = false;
                    rowSel = -1;
                    colSel = -1;
                }

                //if a piece is selected and the tapped space is not a legal move,
                //then leave everything as it is
                else return true;

            }
        }
        //when no piece is currently selected
        else {

            //when the tapped space is not empty and contains a piece
            //that belongs to the human player
            if (state.getWhoseMove() == 0) {
                if (myPieces[row][col] != null && myPieces[row][col].getPlayer() == 0) {
                    this.myPieces[row][col].setSelected(true);
                    havePieceSelected = true;
                    rowSel = row;
                    colSel = col;
                }
            } else {
                if (myPieces[row][col] != null && myPieces[row][col].getPlayer() == 1) {
                    this.myPieces[row][col].setSelected(true);
                    havePieceSelected = true;
                    rowSel = row;
                    colSel = col;
                }
            }
        }

        //redraw board with pieces updated
        gui.invalidate();
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