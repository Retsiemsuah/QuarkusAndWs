package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public interface FrameRepository extends PanacheRepository<Frame> {

    Frame findCurrentFrameFor(GameRun gameRun);
}
