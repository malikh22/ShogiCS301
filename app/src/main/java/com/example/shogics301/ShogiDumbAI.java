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
public class ShogiDumbAI extends GameComputerPlayer implements Tickable {
    //Tag for logging
    private static final String TAG = "ShogiDumbAI";

    public ShogiDumbAI(String name) {
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
        if(info instanceof IllegalMoveInfo)
        {
            return;

        }

        Random rnd = new Random();

        ShogiState state = (ShogiState) info;
        Piece[][] pieces = state.getBoard();
        int pLength = pieces.length;
        Piece p = pieces[0][0];

        boolean myPiece=false;
        while(!myPiece) {
            int i = rnd.nextInt(pLength - 1);
            int j = rnd.nextInt(pieces[i].length - 1);
            p=pieces[i][j];
            if(p instanceof Piece) myPiece = (p.getPlayer()==this.playerNum);
        }

        int destRow = rnd.nextInt(pLength - 1);
        int destCol = rnd.nextInt(pieces[destRow].length - 1);


        // delay for a second to make opponent think we're thinking
        sleep(0.005);

        // Submit our move to the game object. We haven't even checked it it's
        // our turn, or that that position is unoccupied. If it was not our turn,
        // we'll get a message back that we'll ignore. If it was an illegal move,
        // we'll end up here again (and possibly again, and again). At some point,
        // we'll end up randomly pick a move that is legal.
        Log.d("dumb AI", "makes move");
        game.sendAction(new ShogiMoveAction(this, p, destRow, destCol, p.getRow(), p.getColumn()));
    }
}// class ShogiDumbAI
