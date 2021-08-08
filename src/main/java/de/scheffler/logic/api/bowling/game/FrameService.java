package de.scheffler.logic.api.bowling.game;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.GameRun;

public interface FrameService {
    Frame addThrowToCurrentFrame(GameRun runToAddTo, int throwValue);

    Frame addThrowToFrame(Frame frameToAddThrow, int throwValue);

    boolean containsStrike(Frame frame);

    boolean containsSpare(Frame frame);

    void save (Frame frame);
}
