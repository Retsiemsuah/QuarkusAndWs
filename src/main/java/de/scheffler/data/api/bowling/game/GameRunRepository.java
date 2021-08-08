package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface GameRunRepository extends PanacheRepository<GameRun> {
    List<GameRun> findBy(BowlingGame game);

    GameRun findGameRunBy(BowlingGame gameFromDb, int currentRunNumberWithinGame);
}
