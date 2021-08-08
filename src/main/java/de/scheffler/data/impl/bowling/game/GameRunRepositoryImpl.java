package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GameRunRepositoryImpl implements GameRunRepository {

    @Override
    public List<GameRun> findBy(BowlingGame game) {
        return find("bowlingGame",game).list();
    }
}
