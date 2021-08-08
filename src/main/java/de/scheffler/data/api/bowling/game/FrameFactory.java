package de.scheffler.data.api.bowling.game;

import java.util.List;

public interface FrameFactory {

    List<Frame> createEmptyFrame(GameRun newRun);

    Frame createEmptyFrame(GameRun newRun, int framePosition, boolean lastFrameInRun);

}
