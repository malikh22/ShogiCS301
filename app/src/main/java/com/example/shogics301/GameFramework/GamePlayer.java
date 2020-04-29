package com.example.shogics301.GameFramework;

import com.example.shogics301.GameFramework.infoMessage.GameInfo;

/**
 * A player who plays a (generic) game. Each class that implements a player for
 * a particular game should implement this interface.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version July 2013
 */

public interface GamePlayer {

    // sets this player as the GUI player (implemented as final in the
    // major player classes)
    void gameSetAsGui(GameMainActivity activity);

    // sets this player as the GUI player (overrideable)
    void setAsGui(GameMainActivity activity);

    // sends a message to the player
    void sendInfo(GameInfo info);


    // start the player
    void start();

    // whether this player requires a GUI
    boolean requiresGui();

    // whether this player supports a GUI
    boolean supportsGui();

}// interface GamePlayer
