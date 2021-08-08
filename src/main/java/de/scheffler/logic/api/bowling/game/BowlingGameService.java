package de.scheffler.logic.api.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;

import java.util.UUID;

public interface BowlingGameService {

    UUID createNewGame();

    BowlingGame findByUniqueGameId(UUID uniqueGameId);

    void addNewPlayerToGame(UUID gameFromDb, UUID newPlayer);

    void startGame(UUID game);

    void addThrowToGame(int throwValue, UUID uniqueGameId);

}
