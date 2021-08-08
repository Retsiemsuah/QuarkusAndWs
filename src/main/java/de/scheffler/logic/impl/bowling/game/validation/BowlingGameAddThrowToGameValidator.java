package de.scheffler.logic.impl.bowling.game.validation;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.BowlingGameState;
import de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.UUID;

@RequestScoped
public class BowlingGameAddThrowToGameValidator extends ResponseEndpointValidator {

    @Inject
    BowlingGameService bowlingGameService;

    @Override
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
        int throwValue = (Integer) args[0];
        UUID gameId = (UUID) args[1];
        BowlingGame gameFromDb = bowlingGameService.findByUniqueGameId(gameId);
        if (gameFromDb == null) {
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(BowlingGameEndpointMessages.ERROR_NO_VALID_GAME_ID, gameId)).build());
            return this;
        }
        if(gameFromDb.getGameState()!=BowlingGameState.RUNNING){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(BowlingGameEndpointMessages.ERROR_THROW_CANT_BE_ADDED_GAME_NOT_RUNNING, gameId)).build());
            return this;
        }
        if(throwValue<0||throwValue>10)
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(BowlingGameEndpointMessages.ERROR_THROW_HAS_INVALID_VALUE, throwValue)).build());
        return this;
    }

    @Override
    public ResponseEndpointValidator build() {
        return this;
    }
}