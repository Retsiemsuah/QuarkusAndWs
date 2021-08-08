package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GameRunRepositoryImpl implements GameRunRepository {

    @Override
    public List<GameRun> findBy(BowlingGame game) {
        return find("bowlingGame",game).list();
    }

    @Override
    public GameRun findGameRunBy(BowlingGame gameFromDb, int currentRunNumberWithinGame) {
        return find("bowlingGame = :gameToFind and currentRunNumberWithinGame = :numberToFind",
                Parameters.with("gameToFind", gameFromDb).and("numberToFind", currentRunNumberWithinGame)).firstResult();
    }
}
