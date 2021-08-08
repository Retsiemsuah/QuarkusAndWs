package de.scheffler.logic.impl.bowling.game;

import de.scheffler.data.api.bowling.game.*;
import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import de.scheffler.logic.api.bowling.game.GameRunService;
import de.scheffler.logic.api.player.LocalPlayerService;

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
    GameRunService gameRunService;

    @Inject
    GameRunRepository gameRunRepository;

    @Inject
    GameRunFactory gameRunFactory;

    @Inject
    LocalPlayerService playerService;

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
    public void addNewPlayerToGame(UUID gameId, UUID newPlayerId) {
        BowlingGame gameFromDb = findByUniqueGameId(gameId);
        LocalPlayer newPlayer = playerService.findByUniqueId(newPlayerId);
        GameRun newGameRun = gameRunFactory.createEmptyGameRunFor(newPlayer, gameFromDb);
        gameFromDb.addNewGameRun(newGameRun);
        gameRunRepository.persist(newGameRun);
    }

    @Override
    @Transactional
    public void startGame(UUID gameId) {
        if (gameId == null)
            return;
        BowlingGame gameFromDb = bowlingGameRepository.findById(gameId);
        gameFromDb.setState(BowlingGameState.RUNNING);
        gameFromDb.setCurrentRunNumberWithinGame(1);
        bowlingGameRepository.persist(gameFromDb);
    }

    @Override
    @Transactional
    public void addThrowToGame(int throwValue, UUID uniqueGameId) {
        if (uniqueGameId == null)
            return;
        BowlingGame gameFromDb = bowlingGameRepository.findById(uniqueGameId);
        if(gameFromDb.getGameState()!=BowlingGameState.RUNNING)
            return;
        GameRun runToAddThrow = gameRunRepository.findGameRunBy(gameFromDb, gameFromDb.getCurrentRunNumberWithinGame());
        gameRunService.addThrowToGameRunAndCalculateScores(runToAddThrow, throwValue);
        markGameAsFinishedIfNecessary(gameFromDb);
        bowlingGameRepository.persist(gameFromDb);
    }

    private void markGameAsFinishedIfNecessary(BowlingGame gameFromDb) {
        for (GameRun run : gameFromDb.getRegisteredGameRuns())
            if (!run.isGameRunFinished())
                return;
            gameFromDb.setState(BowlingGameState.FINISHED);
    }

}
