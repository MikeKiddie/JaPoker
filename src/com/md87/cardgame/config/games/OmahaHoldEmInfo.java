/*
 * Copyright (c) Chris 'MD87' Smith, 2007. All rights reserved.
 *
 * This code may not be redistributed without prior permission from the
 * aforementioned copyright holder(s).
 */

package com.md87.cardgame.config.games;

import com.md87.cardgame.games.OmahaHoldEm;
import com.md87.cardgame.interfaces.Game;

/**
 *
 * @author Chris
 */
public class OmahaHoldEmInfo extends GameInfo {

    public String getName() {
        return "Omaha Hold'Em";
    }

    public GameType getGameType() {
        return GameInfo.GameType.HOLDEM;
    }

    public int getNumPlayers() {
        return 10;
    }

    public boolean usesBringIns() {
        return false;
    }

    public Game getGame(int numplayers, int bigblind, int ante, int raises) {
        return new OmahaHoldEm(numplayers, bigblind, ante, raises);
    }

}
