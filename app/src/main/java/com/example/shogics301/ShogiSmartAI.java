package com.example.shogics301;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.shogics301.GameFramework.Game;
import com.example.shogics301.GameFramework.GameComputerPlayer;
import com.example.shogics301.GameFramework.GameMainActivity;
import com.example.shogics301.GameFramework.GamePlayer;

import com.example.shogics301.GameFramework.actionMessage.GameOverAckAction;
import com.example.shogics301.GameFramework.actionMessage.MyNameIsAction;
import com.example.shogics301.GameFramework.actionMessage.ReadyAction;
import com.example.shogics301.GameFramework.infoMessage.BindGameInfo;
import com.example.shogics301.GameFramework.infoMessage.GameInfo;
import com.example.shogics301.GameFramework.infoMessage.GameOverInfo;
import com.example.shogics301.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.shogics301.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.shogics301.GameFramework.infoMessage.StartGameInfo;
import com.example.shogics301.GameFramework.infoMessage.TimerInfo;
import com.example.shogics301.GameFramework.utilities.GameTimer;
import com.example.shogics301.GameFramework.utilities.Logger;
import com.example.shogics301.GameFramework.utilities.MessageBox;
import com.example.shogics301.GameFramework.utilities.Tickable;

import java.util.Random;

/**
 * An abstract computerized game player player. This is an abstract class, that
 * should be sub-classed to implement different AIs. The subclass must implement
 * the {@link #receiveInfo} method.
 *
 *
 * @author Steven R. Vegdahl
 * @author Andrew Nuxoll
 * @version July 2013
 */
public class ShogiSmartAI extends GameComputerPlayer implements Tickable {
    //Tag for logging
    private static final String TAG = "ShogiDumbAI";
    private ShogiLegalMoveList myLegalMoves;

    public ShogiSmartAI(String name) {
        // invoke superclass constructor
        super(name); // invoke superclass constructor
    }

    /**
     * Called when the player receives a game-state (or other info) from the
     * game.
     *
     * @param info
     * 		the message from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {

        // if it was a "not your turn" message, just ignore it
        if (info instanceof NotYourTurnInfo) return;
        if (info instanceof IllegalMoveInfo) return;


        myLegalMoves = new ShogiLegalMoveList(playerNum);
        Random rnd = new Random();

        ShogiState state = (ShogiState) info;
        Piece[][] pieces = state.getBoard();
        int pLength = pieces.length;
        Piece selPiece = null;
        int destRow=0;
        int destCol=0;

        //scroll through board

        //check for a checkmate

        //check for a capture

        //make a random move

        for (int i = 0; i < pLength; i++){
            for (int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j] != null && pieces[i][j].getPlayer()==playerNum){
                    for (int desti = 0; desti < pLength; desti++){
                        for (int destj = 0; destj < pieces[i].length; destj++) {
                            if(myLegalMoves.validMove(pieces,pieces[i][j].getType(),i,j,desti,destj,pieces[i][j].getPlayer())){
                                if(pieces[desti][destj]!=null && pieces[desti][destj].getPlayer()!=playerNum){
                                    switch (pieces[desti][destj].getType()) {
                                        case GOLDGENERAL:
                                        case ROOK:
                                        case BISHOP:
                                        case SILVERGENERAL:
                                            if (selPiece == null || (selPiece.getType() != Piece.PieceType.GOLDGENERAL && selPiece.getType() != Piece.PieceType.ROOK && selPiece.getType() != Piece.PieceType.BISHOP && selPiece.getType() != Piece.PieceType.SILVERGENERAL) || rnd.nextBoolean()) {
                                                selPiece = pieces[i][j];
                                                destRow = desti;
                                                destCol = destj;
                                            }
                                            break;
                                        case KING:
                                        case KNIGHT:
                                        case LANCE:
                                            if (selPiece == null || pieces[desti][destj].getType() == Piece.PieceType.PAWN) {
                                                selPiece = pieces[i][j];
                                                destRow = desti;
                                                destCol = destj;
                                            }
                                            break;
                                        case PAWN:
                                            if (selPiece == null) {
                                                selPiece = pieces[i][j];
                                                destRow = desti;
                                                destCol = destj;
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(selPiece==null){
            boolean myP=false;
            int myRow=0;
            Piece myPiece = pieces[0][0];
            while(!myP) {
                myRow=rnd.nextInt(pLength - 1);
                myPiece=pieces[myRow][rnd.nextInt(pieces[myRow].length - 1)];
                if(myPiece!=null) myP = (myPiece.getPlayer()==playerNum);
            }
            selPiece=myPiece;
            destRow = rnd.nextInt(pLength - 1);
            destCol = rnd.nextInt(pieces[destRow].length - 1);
        }
        // delay for a second to make opponent think we're thinking
        sleep(0.1);

        // Submit our move to the game object. We haven't even checked it it's
        // our turn, or that that position is unoccupied. If it was not our turn,
        // we'll get a message back that we'll ignore. If it was an illegal move,
        // we'll end up here again (and possibly again, and again). At some point,
        // we'll end up randomly pick a move that is legal.
        Log.d("smart AI", "makes move");
        game.sendAction(new ShogiMoveAction(this, selPiece, destRow, destCol, selPiece.getRow(), selPiece.getColumn()));
    }

}// class ShogiDumbAI
