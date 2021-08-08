package de.scheffler.logic.api.bowling.game;

import de.scheffler.data.api.bowling.game.GameRun;

public interface GameRunService {

    void addThrowToGameRunAndCalculateScores(GameRun runToAddTo, int throwValue);

    void calculateFrameScores(GameRun gameRun);
}
