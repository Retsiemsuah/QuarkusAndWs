package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.BowlingGameFactory;

import javax.enterprise.context.ApplicationScoped;

import static de.scheffler.data.api.bowling.game.BowlingGameState.NOT_STARTED;

@ApplicationScoped
public class BowlingGameFactoryImpl implements BowlingGameFactory {

    @Override
    public BowlingGame createEmptyGame() {
        BowlingGame newGame = new BowlingGame();
        newGame.setState(NOT_STARTED);
        return newGame;
    }
}
