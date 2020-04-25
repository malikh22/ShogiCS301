package com.example.shogics301;

import com.example.shogics301.GameFramework.LocalGame;
import com.example.shogics301.GameFramework.infoMessage.GameState;
import java.util.ArrayList;


/**
 * Contains the state of a Shogi game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 *
 * @author Steven R. Vegdahl
 * @author Hera Malik
 * @version March 2020
 */
public class ShogiState extends GameState {
    //Tag for logging
    private static final String TAG = "ShogiState";
    private static final long serialVersionUID = 7552321013488624386L;


    ///////////////////////////////////////////////////
    // ************** instance variables ************
    ///////////////////////////////////////////////////

    // the 9x9 array of char that represents the pieces on the board
    private char[][] board = new char[9][9];
    private String history;


    private Piece[][] pieces;
    private Boolean flash = false;
    // an int that tells whose move it is
    private int playerToMove;

    //player 0's captured pieces
    private ArrayList<Piece> drops0 = new ArrayList<>();

    //player 1's captured pieces
    private ArrayList<Piece> drops1 = new ArrayList<>();

    //history of moves
    private ArrayList<ShogiMoveAction> moves;

    private boolean[] playersHaveKing = {true, true};


    /**
     * Constructor for objects of class ShogiState
     */
    ShogiState()
    {
        // initialize the state to be a brand new game
        Piece myPiece;
        pieces = new Piece[9][9];

        //opponent pawns
        int row = 2;
        //for setting up init state
        int col;
        for(col = 0; col < 9; col++){
            myPiece = new Piece(null, Piece.PieceType.PAWN, row, col, 1);
            pieces[row][col] = myPiece;
        }

        //player pawns
        row = 6;
        for(col = 0; col < 9; col++){
            myPiece = new Piece(null, Piece.PieceType.PAWN, row, col, 0);
            pieces[row][col] = myPiece;
        }
        //opponent bishop
        myPiece = new Piece(null, Piece.PieceType.BISHOP, 1, 7, 1);
        pieces[1][7] = myPiece;

        //player bishop
        myPiece = new Piece(null, Piece.PieceType.BISHOP, 7, 1, 0);
        pieces[7][1] = myPiece;

        //opponent rook
        myPiece = new Piece(null, Piece.PieceType.ROOK, 1,1, 1);
        pieces[1][1] = myPiece;

        //player rook
        myPiece = new Piece(null, Piece.PieceType.ROOK, 7,7, 0);
        pieces[7][7] = myPiece;

        //opponent back row
        row = 0;
        for(col = 0; col < 9; col++){
            if(col == 0 || col == 8){
                myPiece = new Piece(null, Piece.PieceType.LANCE, row, col, 1);
                pieces[row][col] = myPiece;
            }else if(col == 1 || col == 7){
                myPiece = new Piece(null, Piece.PieceType.KNIGHT, row, col, 1);
                pieces[row][col] = myPiece;
            }else if(col == 2 || col == 6){
                myPiece = new Piece(null, Piece.PieceType.SILVERGENERAL, row, col, 1);
                pieces[row][col] = myPiece;
            }else if(col == 3 || col == 5){
                myPiece = new Piece(null, Piece.PieceType.GOLDGENERAL, row, col, 1);
                pieces[row][col] = myPiece;
            }else{
                myPiece = new Piece(null, Piece.PieceType.KING, row, col, 1);
                pieces[row][col] = myPiece;
            }
        }
        //player back row
        row = 8;
        for(col = 0; col < 9; col++){
            if(col == 0 || col == 8){
                myPiece = new Piece(null, Piece.PieceType.LANCE, row, col, 0);
                pieces[row][col] = myPiece;
            }else if(col == 1 || col == 7){
                myPiece = new Piece(null, Piece.PieceType.KNIGHT, row, col, 0);
                pieces[row][col] = myPiece;
            }else if(col == 2 || col == 6){
                myPiece = new Piece(null, Piece.PieceType.SILVERGENERAL, row, col, 0);
                pieces[row][col] = myPiece;
            }else if(col == 3 || col == 5){
                myPiece = new Piece(null, Piece.PieceType.GOLDGENERAL, row, col, 0);
                pieces[row][col] = myPiece;
            }else{
                myPiece = new Piece(null, Piece.PieceType.KING, row, col, 0);
                pieces[row][col] = myPiece;
            }
        }
        // make it player 0's move
        playerToMove = 0;
    }// constructor

    /**
     * Copy constructor for class ShogiState
     *
     * @param original
     * 		the ShogiState object that we want to clone
     */
    ShogiState(ShogiState original)
    {
        // create a new 9x9 array, and copy the values from
        // the original
        board = new char[9][9];
        pieces = new Piece[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = original.board[i][j];
                pieces[i][j] = original.pieces[i][j];
            }
        }

        // copy the player-to-move information
        playerToMove = original.playerToMove;

        //copy player 0's captured pieces
        drops0 = original.drops0;

        //copy player 1's captured pieces
        drops1 = original.drops1;

        //copy history of moves
        moves = original.moves;

        //copy original check for kings
        playersHaveKing = original.playersHaveKing;


    }

    /**
     * Find out which piece is on a square
     *
     * @param row
     *		the row being queried
     * @param col
     * 		the column being queried
     * @return
     * 		the piece at the given square; ' ' if no piece there;
     * 		'?' if it is an illegal square
     */
    public char getPiece(int row, int col) {
        // if we're out of bounds or anything, return '?';
        if (board == null || row < 0 || col < 0) return '?';
        if (row >= board.length || col >= board[row].length) return '?';

        // return the piece that is in the proper position
        return board[row][col];
    }

    /**
     * Sets a piece on a square
     *
     * @param row
     * 		the row being queried
     * @param
     * 		col the column being queried
     * @param
     * 		piece the piece to place
     */
    void setPiece(int row, int col, char piece) {
        // if we're out of bounds or anything, return;
        if (board == null || row < 0 || col < 0) return;
        if (row >= board.length || col >= board[row].length) return;

        // return the character that is in the proper position
        board[row][col] = piece;
    }

    /**
     * Tells whose move it is.
     *
     * @return the index (0 or 1) of the player whose move it is.
     */
    int getWhoseMove() {
        return playerToMove;
    }

    /**
     * set whose move it is
     * @param id
     * 		the player we want to set as to whose move it is
     */
    void setWhoseMove(int id) {
        playerToMove = id;
    }

    public ArrayList<ShogiMoveAction> getMoves() { return moves; }
    public void recordHistory(ShogiMoveAction move) { moves.add(move); }

    public ArrayList<Piece> getDrops0() {return drops0;}
    public ArrayList<Piece> getDrops1() {return drops1;}
    public void capturep0 (Piece captured) { drops0.add(captured); }
    public void capturep1 (Piece captured) { drops1.add(captured); }


    public Piece[][] getBoard() { return pieces; }
    public void setBoard(Piece[][] pieces) { this.pieces = pieces; }

    public boolean getPlayerHasKing(int i) { return playersHaveKing[i]; }
    public void setPlayerHasKing(int i) { playersHaveKing[i] = !playersHaveKing[i]; }

    /**
     * This method determines if a players based on a location
     *
     * @param idx the index of the player whom we want to know is in check
     * @param board the state of the board in which we want to determine
     *              if the specified player is in check
     * @param row takes row of where the king is
     * @param col take col of where the king is
     *
     * @return true if the specified player's king is in check, false if not
     */
    public boolean determinePlayerInCheck(int idx, Piece[][] board, int row, int col) {

        //necessary variables
        int r = 0, c = 0; //for iterating through the board
        //ShogiPiece king = board[row][col]; //the to-be-found king of the specified player
        int thisPlayerPiece; //for determining if the king is the specified player's
        boolean playerInCheck = false;

        //determine which player's piece we should be looking for
        if(idx == 0) thisPlayerPiece = 0;
        else thisPlayerPiece = 1;


        //determine if the king is in check. Either way, update the gamestate
        // so that it reflects this player's status of check
        for(r = 0; r < 9; r++) {
            for(c = 0; c < 9; c++) {
                if (board[r][c] != null &&
                        board[r][c].getPlayer() != thisPlayerPiece &&
                        board[r][c].legalMove(board, row, col)) {

                    playerInCheck = true;
                    break;
                }
            }
            if(playerInCheck) break; //don't continue if player is already in check
        }


        setPlayerInCheck(idx, playerInCheck);

        //Log.i("ShogiLocalGame", "player " + idx + " in check: " + getPlayerInCheck(idx));

        return playerInCheck;

    }

    private void setPlayerInCheck(int idx, boolean playerInCheck) {
    }

    public void setFlash (Boolean b){

        this.flash = b;

    }

    public Boolean getFlash()
    {

        return this.flash;
    }

    public void setDrops0(ArrayList<Piece> drops0) {
        this.drops0 = drops0;
    }

    public void setDrops1(ArrayList<Piece> drops1) {
        this.drops1 = drops1;
    }

    public void setHistory(String history)
    {

        this.history = history;

    }

    public String getHistory()
    {

        return this.history;

    }
}