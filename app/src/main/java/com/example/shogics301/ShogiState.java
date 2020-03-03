package com.example.shogics301;

import com.example.shogics301.GameFramework.infoMessage.GameState;


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
    private char[][] board;
    
    // an int that tells whose move it is
    private int playerToMove;

    /**
     * Constructor for objects of class ShogiState
     */
    public ShogiState()
    {
        // initialize the state to be a brand new game
        board = new char[9][9];
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		board[i][j] = ' ';
        	}
        }
        
        // make it player 0's move
        playerToMove = 0;
    }// constructor
    
    /**
     * Copy constructor for class ShogiState
     *  
     * @param original
     * 		the ShogiState object that we want to clong
     */
    public ShogiState(ShogiState original)
    {
    	// create a new 9x9 array, and copy the values from
    	// the original
    	board = new char[9][9];
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			board[i][j] = original.board[i][j];
    		}
    	}
    	
    	// copy the player-to-move information
        playerToMove = original.playerToMove;
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
    public void setPiece(int row, int col, char piece) {
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
    public int getWhoseMove() {
        return playerToMove;
    }
    
    /**
     * set whose move it is
     * @param id
     * 		the player we want to set as to whose move it is
     */
    public void setWhoseMove(int id) {
    	playerToMove = id;
    }
}
