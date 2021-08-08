package de.scheffler.data.api.bowling.game;

import de.scheffler.data.api.player.LocalPlayer;

public interface GameRunFactory {
    GameRun createEmptyGameRunFor(LocalPlayer newPlayer, BowlingGame gameFromDb);
}
