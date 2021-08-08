package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.*;
import de.scheffler.data.api.player.LocalPlayer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GameRunFactoryImpl implements GameRunFactory {

    @Inject
    FrameFactory throwHistoryFactory;

    @Inject
    GameRunRepository gameRunRepository;

    @Override
    public GameRun createEmptyGameRunFor(LocalPlayer newPlayer, BowlingGame gameFromDb) {
        GameRun newRun = new GameRun();
        gameRunRepository.persist(newRun);
        newRun.setBowlingGame(gameFromDb);
        newRun.setPlayer(newPlayer);
        newRun.setRunNumberWithinGame(gameFromDb.getRegisteredGameRuns().size()+1);
        newRun.setFrameHistory(throwHistoryFactory.createEmptyFrame(newRun));
        gameRunRepository.persist(newRun);
        return newRun;
    }
}
