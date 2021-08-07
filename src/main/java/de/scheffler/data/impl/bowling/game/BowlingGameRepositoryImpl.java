package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.BowlingGameRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class BowlingGameRepositoryImpl implements BowlingGameRepository {

    @Override
    public BowlingGame findById(UUID idAsUuid) {
        return find("id", idAsUuid).firstResult();
    }
}
