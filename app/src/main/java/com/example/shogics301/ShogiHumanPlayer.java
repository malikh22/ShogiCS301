package com.example.shogics301;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.shogics301.GameFramework.GameHumanPlayer;
import com.example.shogics301.GameFramework.GameMainActivity;

import com.example.shogics301.GameFramework.Rules;
import com.example.shogics301.GameFramework.infoMessage.GameInfo;
import com.example.shogics301.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.shogics301.GameFramework.utilities.Logger;

import java.util.ArrayList;

import static android.graphics.Color.rgb;


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
    private Button toRules;
    private Button toDrops;
    Piece toDrop = null;
    boolean amDropping = false;
    boolean droppedThisTurn = false;

    private Button rulesButtonMoving;
    private Button rulesButtonPromotion;
    private Button rulesButtonToGame;
    private Button dropsButtonToGame;
    private Button movingButtonToGame;
    private Button promotionButtonToGame;
    private Button historyButton;
    private Button historyButtonToGame;

    private boolean usingRulesScreen = false;
    private boolean usingDropsScreen = false;
    private boolean usingHistoryScreen = false;
    private TextView historyText;
    private MediaPlayer mediaPlayer;



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

            if (usingRulesScreen) return;
            if (usingDropsScreen) return;
            if (usingHistoryScreen) return;


            gui = (ShogiGui) myActivity.findViewById(R.id.shogiBoard);
            gui.myPieces = this.myPieces;

            if (info instanceof IllegalMoveInfo) {

                Log.d("ShogiHP", "illegal move");


                if (super.getflash()) {

                    try {
                        flashButton();
                    } catch (InterruptedException e) {


                    }
                }

                gui.invalidate();
            }

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
        mediaPlayer = MediaPlayer.create(myActivity.getContext(), R.raw.errorsound);
        myActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        topView.setOnTouchListener(this);

        toRules = (Button) myActivity.findViewById(R.id.button2);
        toRules.setBackgroundColor(rgb(216, 188, 94));

        toRules.setTextColor(Color.BLACK);
        Log.d("attempt to open rules", "open rules");

        historyButton = (Button) myActivity.findViewById(R.id.button);
        historyButton.setBackgroundColor(rgb(216, 188, 94));


        toRules.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                Logger.log("rules click", "rules button clicked");
                Log.d("on click", "on cliked");
                openRules();

            }
        });

        toDrops = (Button) myActivity.findViewById(R.id.button4);
        toDrops.setBackgroundColor(rgb(216, 188, 94));
        toDrops.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                Logger.log("drops click", "drops button clicked");
                Log.d("on click", "on clicked");
                openDrops();

            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                Logger.log("drops click", "drops button clicked");
                Log.d("on click", "on clicked");
                openHistory();

            }
        });

        // GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

    @SuppressLint("SetTextI18n")
    public void openDrops() {


//        Intent intent = new Intent(myActivity, Rules.class);

        usingDropsScreen = true;
        myActivity.setContentView(R.layout.dropping);
        dropsButtonToGame = (Button) myActivity.findViewById(R.id.button9);
        final Button confirmButton = (Button) myActivity.findViewById(R.id.button11);
        ImageButton pawnButton = (ImageButton) myActivity.findViewById(R.id.imageButton4);
        ImageButton rookButton = (ImageButton) myActivity.findViewById(R.id.imageButton5);
        ImageButton bishopButton = (ImageButton) myActivity.findViewById(R.id.imageButton);
        ImageButton sgButton = (ImageButton) myActivity.findViewById(R.id.imageButton6);
        ImageButton ggButton = (ImageButton) myActivity.findViewById(R.id.imageButton2);
        ImageButton knightButton = (ImageButton) myActivity.findViewById(R.id.imageButton3);
        ImageButton lanceButton = (ImageButton) myActivity.findViewById(R.id.imageButton7);

        final ArrayList<Piece> myDrops = state.getDrops0();
        final ArrayList<Piece> oppDrops = state.getDrops1();
        int myRCount = 0;
        int myBCount = 0;
        int myLCount = 0;
        int myKCount = 0;
        int myGGCount = 0;
        int mySGCount = 0;
        int myPCount = 0;

        int oppRCount = 0;
        int oppBCount = 0;
        int oppLCount = 0;
        int oppKCount = 0;
        int oppGGCount = 0;
        int oppSGCount = 0;
        int oppPCount = 0;

        final TextView mySelected = myActivity.findViewById(R.id.textView46);
        TextView myPawns = myActivity.findViewById(R.id.textView47);
        TextView myRooks = myActivity.findViewById(R.id.textView48);
        TextView myLances = myActivity.findViewById(R.id.textView60);
        TextView myKnights = myActivity.findViewById(R.id.textView52);
        TextView myBishops = myActivity.findViewById(R.id.textView49);
        TextView myGGs = myActivity.findViewById(R.id.textView51);
        TextView mySGs = myActivity.findViewById(R.id.textView50);

        TextView oppPawns = myActivity.findViewById(R.id.textView54);
        TextView oppRooks = myActivity.findViewById(R.id.textView55);
        TextView oppLances = myActivity.findViewById(R.id.textView61);
        TextView oppKnights = myActivity.findViewById(R.id.textView57);
        TextView oppBishops = myActivity.findViewById(R.id.textView56);
        TextView oppGGs = myActivity.findViewById(R.id.textView59);
        TextView oppSGs = myActivity.findViewById(R.id.textView58);


        //disable all buttons to start
        pawnButton.setEnabled(false);
        rookButton.setEnabled(false);
        bishopButton.setEnabled(false);
        sgButton.setEnabled(false);
        ggButton.setEnabled(false);
        lanceButton.setEnabled(false);
        knightButton.setEnabled(false);

        for (Piece p : oppDrops) {
            if (p.getType() == Piece.PieceType.BISHOP) {
                oppBCount++;
            }
            if (p.getType() == Piece.PieceType.ROOK) {
                oppRCount++;
            }
            if (p.getType() == Piece.PieceType.PAWN) {
                oppPCount++;
            }
            if (p.getType() == Piece.PieceType.LANCE) {
                oppLCount++;
            }
            if (p.getType() == Piece.PieceType.GOLDGENERAL) {
                oppGGCount++;
            }
            if (p.getType() == Piece.PieceType.SILVERGENERAL) {
                oppSGCount++;
            }
            if (p.getType() == Piece.PieceType.KNIGHT) {
                oppKCount++;
            }
        }

        for (Piece p : myDrops) {
            if (p.getType() == Piece.PieceType.BISHOP) {
                myBCount++;
                bishopButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.ROOK) {
                myRCount++;
                rookButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.PAWN) {
                myPCount++;
                pawnButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.LANCE) {
                myLCount++;
                lanceButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.GOLDGENERAL) {
                myGGCount++;
                ggButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.SILVERGENERAL) {
                mySGCount++;
                sgButton.setEnabled(true);
            }
            if (p.getType() == Piece.PieceType.KNIGHT) {
                myKCount++;
                knightButton.setEnabled(true);
            }
        }

        myLances.setText("Lances:" + " " + myLCount);
        myRooks.setText("Rooks:" + " " + myRCount);
        myPawns.setText("Pawns:" + " " + myPCount);
        myBishops.setText("Bishops:" + " " + myBCount);
        myGGs.setText("Gold Generals:" + " " + myGGCount);
        mySGs.setText("Silver Generals:" + " " + mySGCount);
        myKnights.setText("Knights:" + " " + myKCount);

        oppLances.setText("Lances:" + " " + oppLCount);
        oppRooks.setText("Rooks:" + " " + oppRCount);
        oppPawns.setText("Pawns:" + " " + oppPCount);
        oppBishops.setText("Bishops:" + " " + oppBCount);
        oppGGs.setText("Gold Generals:" + " " + oppGGCount);
        oppSGs.setText("Silver Generals:" + " " + oppSGCount);
        oppKnights.setText("Knights:" + " " + oppKCount);


        pawnButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.PAWN) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Pawn");
                        confirmButton.setEnabled(true);
                    }
                }
        );
        rookButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.ROOK) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Rook");
                        confirmButton.setEnabled(true);
                    }
                }


        );
        bishopButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.BISHOP) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Bishop");
                        confirmButton.setEnabled(true);
                    }
                }


        );
        sgButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.SILVERGENERAL) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Silver General");
                        confirmButton.setEnabled(true);
                    }
                }
        );
        ggButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.GOLDGENERAL) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Gold General");
                        confirmButton.setEnabled(true);
                    }
                }
        );

        lanceButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.LANCE) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Lance");
                        confirmButton.setEnabled(true);
                    }
                }
        );

        knightButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        for (Piece p : myDrops) {
                            if (p.getType() == Piece.PieceType.KNIGHT) {
                                toDrop = p;
                            }
                        }
                        mySelected.setText("Selected: Knight");
                        confirmButton.setEnabled(true);
                    }
                }
        );

        if(toDrop == null){
            confirmButton.setEnabled(false);
        }

        dropsButtonToGame.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        mySelected.setText("Selected:");
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingDropsScreen = false;
                        toDrop = null;
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                }
        );

        confirmButton.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        mySelected.setText("Selected:");
                        amDropping = true;
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingDropsScreen = false;
                        ArrayList<Piece> updated = state.getDrops0();
                        updated.remove(toDrop);
                        state.setDrops0(updated);
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                });
    }

    public void openHistory() {
        usingHistoryScreen = true;
        myActivity.setContentView(R.layout.history);
        historyButtonToGame = (Button) myActivity.findViewById(R.id.button8);
        historyText = (TextView) myActivity.findViewById(R.id.textView46);
        historyText.setText(state.getHistory());
        historyText.setMovementMethod(new ScrollingMovementMethod());
        gui.invalidate();
        historyButtonToGame.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingHistoryScreen = false;
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                });
    }

    public void openRules() {
        usingRulesScreen = true;
        myActivity.setContentView(R.layout.rules);
        rulesButtonToGame = (Button) myActivity.findViewById(R.id.button7);
        rulesButtonMoving = (Button) myActivity.findViewById(R.id.button3);
        rulesButtonPromotion = (Button) myActivity.findViewById(R.id.button5);

        rulesButtonToGame.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingRulesScreen = false;
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                });

        rulesButtonMoving.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        openMoving();
                    }
                }


        );

        rulesButtonPromotion.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {

                        openPromotion();
                    }
                }


        );

        Log.d("attempt to open rules", "open rules");
//        myActivity.startActivity(intent);
    }

    public void openMoving() {
        myActivity.setContentView(R.layout.moving);
        movingButtonToGame = (Button) myActivity.findViewById(R.id.button10);

        movingButtonToGame.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingRulesScreen = false;
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                });
    }

    public void openPromotion() {
        myActivity.setContentView(R.layout.promotion);
        promotionButtonToGame = (Button) myActivity.findViewById(R.id.button6);

        promotionButtonToGame.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View v) {
                        ShogiHumanPlayer.this.setAsGui(myActivity);
                        usingRulesScreen = false;
                        if (state != null) {
                            receiveInfo(state);
                        }
                    }
                }


        );


    }

    public void flashButton() throws InterruptedException {
        toRules.setBackgroundColor(Color.RED);
        toRules.setTextColor(Color.RED);
        gui.invalidate();

        Log.d("ShogiHumanPlayer", "flash");
        Thread.sleep(400);

        toRules.setBackgroundColor(Color.WHITE);
        toRules.setTextColor(Color.BLACK);
        gui.invalidate();

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

        if (amDropping && toDrop != null && myPieces[row][col] == null) {
            row = (int) ((event.getY() - ShogiGui.topLeftY) / (ShogiGui.space));
            col = (int) ((event.getX() - ShogiGui.topLeftX) / (ShogiGui.space));
            game.sendAction(new ShogiDropAction(this, toDrop, row, col));
            amDropping = false;
            droppedThisTurn = true;
            //remove the dropped piece
            gui.invalidate();
        }

        //when a piece on the board is currently selected
        if (havePieceSelected && !amDropping) {

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
                    droppedThisTurn = false;
                    //reset
                    havePieceSelected = false;
                    rowSel = -1;
                    colSel = -1;
                }

                //if a piece is selected and the tapped space is not a legal move,
                //then leave everything as it is
                else {
                    Log.d("ShogiHP", "flash");
                    //make noise
                    mediaPlayer.start();

                }

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
        droppedThisTurn = false;
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