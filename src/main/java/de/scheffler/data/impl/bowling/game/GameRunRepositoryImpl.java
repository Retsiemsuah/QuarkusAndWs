package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class GameRunRepositoryImpl implements GameRunRepository {

    @Override
    public List<GameRun> findBy(BowlingGame game) {
        return find("bowlingGame",game).list();
    }

    @Override
    @Transactional
    public GameRun findGameRunBy(BowlingGame gameFromDb, int currentRunNumberWithinGame) {
        Map<String, Object> params = new HashMap<>();
        params.put("gameToFind", gameFromDb);
        params.put("runNumberWithinGame", currentRunNumberWithinGame);
        return find("bowlingGame = :gameToFind and runNumberWithinGame = :runNumberWithinGame",params).firstResult();
    }
}
