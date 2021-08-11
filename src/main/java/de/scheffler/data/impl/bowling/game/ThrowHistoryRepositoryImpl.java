package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.FrameRepository;
import de.scheffler.data.api.bowling.game.GameRun;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ThrowHistoryRepositoryImpl implements FrameRepository {

    @Override
    public List<Frame> findBy(GameRun game) {
        return find("belongingToRun = ?1 ORDER BY framePosition",game).list();
    }

    @Override
    public Frame findCurrentFrameFor(GameRun gameRun) {
        Map<String, Object> params = new HashMap<>();
        params.put("belongingToRun", gameRun);
        params.put("frameIsFinished", false);
        return find("belongingToRun = :belongingToRun and frameIsFinished = :frameIsFinished ORDER BY framePosition", params).firstResult();
    }
}
