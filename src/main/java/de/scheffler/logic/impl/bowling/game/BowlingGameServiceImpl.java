package de.scheffler.logic.impl.bowling.game;

import de.scheffler.data.api.bowling.game.*;
import de.scheffler.data.api.player.Player;
import de.scheffler.logic.api.bowling.game.BowlingGameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class BowlingGameServiceImpl implements BowlingGameService {

    @Inject
    BowlingGameRepository bowlingGameRepository;

    @Inject
    BowlingGameFactory bowlingGameFactory;

    @Inject
    GameRunRepository gameRunRepository;

    @Inject
    GameRunFactory gameRunFactory;

    @Override
    @Transactional
    public UUID createNewGame() {
        BowlingGame newGame = bowlingGameFactory.createEmptyGame();
        bowlingGameRepository.persist(newGame);
        return newGame.getId();
    }

    @Override
    @Transactional
    public BowlingGame findByUniqueGameId(UUID uniqueGameId) {
        return bowlingGameRepository.findById(uniqueGameId);
    }

    @Override
    @Transactional
    public void save(BowlingGame game) { bowlingGameRepository.persist(game);  }

    @Override
    @Transactional
    public void addNewPlayerToGame(BowlingGame gameFromDb, Player newPlayer) {
        GameRun newGameRun = gameRunFactory.createEmptyGameRunFor(newPlayer);
        gameFromDb.addNewGameRun(newGameRun);
        gameRunRepository.persist(newGameRun);
    }
}
