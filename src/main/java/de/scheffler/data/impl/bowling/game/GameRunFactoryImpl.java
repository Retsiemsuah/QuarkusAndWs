package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.*;
import de.scheffler.data.api.player.LocalPlayer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GameRunFactoryImpl implements GameRunFactory {

    @Inject
    ThrowHistoryFactory throwHistoryFactory;

    @Inject
    GameRunRepository gameRunRepository;

    @Override
    public GameRun createEmptyGameRunFor(LocalPlayer newPlayer, BowlingGame gameFromDb) {
        GameRun newRun = new GameRun();
        newRun.setBowlingGame(gameFromDb);
        newRun.setPlayer(newPlayer);
        newRun.setRunNumberWithinGame(gameFromDb.getRegisteredGameRuns().size()+1);
        List<ThrowHistory> emptyHistoryList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
            emptyHistoryList.add(throwHistoryFactory.createEmptyThrowHistroyFor(newRun));

        newRun.setThrowHistoryList(emptyHistoryList);
        gameRunRepository.persist(newRun);
        return newRun;
    }
}
