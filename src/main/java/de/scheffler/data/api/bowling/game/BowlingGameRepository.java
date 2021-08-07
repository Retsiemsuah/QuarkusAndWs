package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.UUID;

public interface BowlingGameRepository extends PanacheRepository<BowlingGame> {

    BowlingGame findById(UUID idAsString);
}
