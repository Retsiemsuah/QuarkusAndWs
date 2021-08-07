package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.*;
import de.scheffler.data.api.player.Player;

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
    public GameRun createEmptyGameRunFor(Player newPlayer) {
//        GameRun newRun = new GameRun(newPlayer);
//        List<ThrowHistory> emptyHistoryList = new ArrayList<>();
//        for(int i = 0 ; i < 10 ; i++)
//            emptyHistoryList.add(throwHistoryFactory.createEmptyThrowHistroyFor(newRun));
//        newRun.setThrowHistoryList(emptyHistoryList);
//        gameRunRepository.persist(newRun);
        return null;
    }
}
