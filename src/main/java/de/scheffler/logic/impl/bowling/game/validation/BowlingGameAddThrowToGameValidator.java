package de.scheffler.logic.impl.bowling.game.validation;

import de.scheffler.logic.api.bowling.game.BowlingGameService;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class BowlingGameAddThrowToGameValidator extends ResponseEndpointValidator {

    @Inject
    BowlingGameService bowlingGameService;

    @Override
    public ResponseEndpointValidator paramsAreLogicallyValid(Object... args) {
//        if (args[0] == null || args[1] == null) {
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(ADDING_PLAYER_FAILED_MISSING_PARAMS).build());
//            return this;
//        }
//        UUID gameId = (UUID) args[0];
//        UUID playerId = (UUID) args[1];
//        BowlingGame gameFromDb = bowlingGameService.findByUniqueGameId(gameId);
//        if (gameFromDb == null) {
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(NO_VALID_GAME_ID_RESPONSE_TEXT, gameId)).build());
//            return this;
//        }
//
//        LocalPlayer player = playerService.findByUniqueId(playerId);
//        if(player== null){
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(NO_VALID_PLAYER_ID_RESPONSE, playerId)).build());
//            return this;
//        }
//        if (gameFromDb.getRegisteredGameRuns().size() >= 6) {
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(MAX_PLAYER_LIMIT_ALREADY_REACHED, gameId)).build());
//            return this;
//        }
//        if (gameFromDb.getRegisteredGameRuns().contains(playerId)) {
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(MAX_PLAYER_LIMIT_ALREADY_REACHED, gameId)).build());
//            return this;
//        }
//        if(gameFromDb.getGameState()!= BowlingGameState.NOT_STARTED){
//            setErrorResponse(Response.status(HttpStatus.SC_BAD_REQUEST).entity(String.format(PLAYER_CANT_BE_ADDED_GAME_ALREADY_STARTED, playerId)).build());
//            return this;
//        }
        return this;
    }

    @Override
    public ResponseEndpointValidator build() {
        return this;
    }
}