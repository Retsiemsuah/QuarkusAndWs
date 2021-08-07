package de.scheffler.logic.api.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.player.Player;

import java.util.UUID;

public interface BowlingGameService {

    UUID createNewGame();

    BowlingGame findByUniqueGameId(UUID uniqueGameId);

    void save(BowlingGame gameFromDb);

    void addNewPlayerToGame(BowlingGame gameFromDb, Player newPlayer);
}
