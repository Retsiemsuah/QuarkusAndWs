package de.scheffler.logic.impl.bowling.game.validation;

import de.scheffler.data.api.bowling.game.BowlingGame;
import de.scheffler.data.api.bowling.game.BowlingGameState;
import de.scheffler.data.api.player.LocalPlayer;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import de.scheffler.logic.api.player.LocalPlayerService;
import org.apache.http.HttpStatus;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import java.util.UUID;

import static de.scheffler.endpoint.api.bowlinggame.dto.BowlingGameEndpointMessages.*;

@RequestScoped
public class BowlingGameAddNewPlayerValidator extends ResponseEndpointValidator {

    @Inject
    BowlingGameService bowlingGameService;

    @Inject
    LocalPlayerService playerService;

    @Override
    @Transactional
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
        UUID gameId = (UUID) args[0];
        UUID playerId = (UUID) args[1];

        BowlingGame gameFromDb = bowlingGameService.findByUniqueGameId(gameId);
        if (gameFromDb == null) {
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(ERROR_NO_VALID_GAME_ID, gameId)).build());
            return this;
        }

        LocalPlayer player = playerService.findByUniqueId(playerId);
        if(player== null){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(ERROR_NO_VALID_PLAYER_ID, playerId)).build());
            return this;
        }
        if (gameFromDb.getRegisteredGameRuns().size() >= 6) {
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(ERROR_MAX_PLAYER_LIMIT_ALREADY_REACHED, gameId)).build());
            return this;
        }
        if(gameFromDb.getGameState()!= BowlingGameState.NOT_STARTED){
            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(ERROR_PLAYER_CANT_BE_ADDED_GAME_ALREADY_STARTED, playerId)).build());
            return this;
        }
        return this;
    }

    @Override
    public ResponseEndpointValidator build() {
        return this;
    }
}