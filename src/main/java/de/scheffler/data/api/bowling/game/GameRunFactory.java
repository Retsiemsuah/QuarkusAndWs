package de.scheffler.data.api.bowling.game;

import de.scheffler.data.api.player.Player;

public interface GameRunFactory {
    GameRun createEmptyGameRunFor(Player newPlayer);
}
