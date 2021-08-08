package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.ThrowHistory;
import de.scheffler.data.api.bowling.game.ThrowHistoryFactory;
import de.scheffler.data.api.bowling.game.ThrowHistoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ThrowHistoryFactoryImpl implements ThrowHistoryFactory {
    @Inject
    ThrowHistoryRepository throwHistoryRepository;

    @Override
    public ThrowHistory createEmptyThrowHistroyFor(GameRun newRun) {
        ThrowHistory newHist = new ThrowHistory(newRun);
        throwHistoryRepository.persist(newHist);
        return newHist;
    }
}
