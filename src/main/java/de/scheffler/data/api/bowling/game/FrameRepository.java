package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public interface FrameRepository extends PanacheRepository<Frame> {

    List<Frame> findBy(GameRun game);

    Frame findCurrentFrameFor(GameRun gameRun);
}
