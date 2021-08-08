package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.FrameRepository;
import de.scheffler.data.api.bowling.game.GameRun;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ThrowHistoryRepositoryImpl implements FrameRepository {

    @Override
    public Frame findCurrentFrameFor(GameRun gameRun) {
        return find("belongingToRun = :frameBelongigToRun and frameIsFinished = false ORDER BY framePosition ASC"
                , Parameters.with("gameToFind", gameRun)).firstResult();
    }
}
