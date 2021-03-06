package com.example.shogics301;

import com.example.shogics301.GameFramework.GameMainActivity;
import com.example.shogics301.GameFramework.GamePlayer;
import com.example.shogics301.GameFramework.LocalGame;
import com.example.shogics301.GameFramework.gameConfiguration.GameConfig;
import com.example.shogics301.GameFramework.gameConfiguration.GamePlayerType;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * This is the class for the main activity. Handles player and game creation.
 *
 */
public class ShogiMainActivity extends GameMainActivity implements Serializable {
    private static final long serialVersionUID = 42978563847L;
    private static final int PORT_NUMBER = 3452;

    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // a human player player type (player type 0)
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new ShogiHumanPlayer(name);
            }});

        // a computer player type (player type 1)
        playerTypes.add(new GamePlayerType("Dumb Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new ShogiDumbAI(name);
            }});

        // a computer player type (player type 2)
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) { return new ShogiSmartAI(name); }});

        // Create a game configuration class for Shogi:
        // - player types as given above
        // - from 1 to 2 players
        // - name of game is "Counter Game"
        // - port number as defined above
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2,
                "Shogi", PORT_NUMBER);


        // Add the default players to the configuration
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player

        // Set the default remote-player setup:
        // - player name: "Remote Player"
        // - IP code: (empty string)
        // - default player type: human player
        defaultConfig.setRemoteData("Remote Player", "", 0);

        // return the configuration
        return defaultConfig;
    }

    public LocalGame createLocalGame() {
        return new ShogiLocalGame();
    }

}