package de.scheffler.endpoint.impl.bowlinggame;

import de.scheffler.endpoint.api.bowlinggame.BowlingGameEndpoint;
import de.scheffler.endpoint.impl.bowlinggame.dto.BowlingGameDtoMapper;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import de.scheffler.logic.api.bowling.game.validation.BowlingGameEndpointValidatorProvider;
import de.scheffler.logic.api.bowling.game.BowlingGameService;
import org.apache.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.UUID;

public class BowlingGameEndpointImpl implements BowlingGameEndpoint {

    @Inject
    BowlingGameEndpointValidatorProvider validatorProvider;

    @Inject
    BowlingGameService bowlingGameService;

    @Inject
    BowlingGameDtoMapper bowlingGameDtoMapper;

    @Override
    public Response createNewGame() {
        UUID bowlingId = bowlingGameService.createNewGame();
        return bowlingId == null ? Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build() : Response.ok(bowlingId).build();
    }

    @Override
    public Response addNewPlayerToGame(UUID uniqueGameId, UUID playerId) {
        ResponseEndpointValidator validator = validatorProvider.addNewPlayerValidator();
        if (validator.afterValidationOf(uniqueGameId, playerId).preparedErrorResponseIsNotEmpty())
            return validator.getErrorResponse();
        bowlingGameService.addNewPlayerToGame(uniqueGameId, playerId);
        return Response.ok().build();
    }

    @Override
    public Response findById(UUID gameId) {
        ResponseEndpointValidator validator = validatorProvider.newFindByIdValidator();
        if (validator.afterValidationOf(gameId).preparedErrorResponseIsNotEmpty())
            return validator.getErrorResponse();
        return Response.ok().entity(bowlingGameDtoMapper.createDtoFrom(bowlingGameService.findByUniqueGameId(gameId))).build();
    }

    @Override
    public Response startGame(UUID gameId) {
        ResponseEndpointValidator validator = validatorProvider.newStartGameValidator();
        if (validator.afterValidationOf(gameId).preparedErrorResponseIsNotEmpty())
            return validator.getErrorResponse();
        bowlingGameService.startGame(gameId);
        return Response.ok().build();
    }

    @Override
    public Response addThrowToGame(int throwValue, UUID uniqueGameId) {
        ResponseEndpointValidator validator = validatorProvider.newAddThrowToGameValidator();
        if (validator.afterValidationOf(throwValue, uniqueGameId).preparedErrorResponseIsNotEmpty())
            return validator.getErrorResponse();
        bowlingGameService.addThrowToGame(throwValue,uniqueGameId);
        return Response.ok().build();
    }

}
