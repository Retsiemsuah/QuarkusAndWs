package de.scheffler.endpoint.impl.player;

import de.scheffler.endpoint.api.player.PlayerEndpoint;
import de.scheffler.endpoint.api.player.dto.PlayerDto;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;
import de.scheffler.logic.api.player.validation.PlayerEndpointValidatorProvider;
import de.scheffler.logic.api.player.LocalPlayerService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class PlayerEndpointImpl implements PlayerEndpoint {

    @Inject
    PlayerEndpointValidatorProvider validatorProvider;

    @Inject
    PlayerDtoMapper dtoMapper;

    @Inject
    LocalPlayerService playerService;

    @Override
    public Response createNewPlayer(PlayerDto player) {
        ResponseEndpointValidator validator = validatorProvider.newCreateNewPlayerValidator();
        if(validator.afterValidationOf(player).preparedErrorResponseIsNotEmpty()){
            return validator.getErrorResponse();
        }
        return Response.ok().entity(playerService.persistNewLocalPlayer(dtoMapper.from(player))).build();
    }

    @Override
    public Response findById(UUID playerId) {
        ResponseEndpointValidator validator = validatorProvider.newFindByIdValidator();
        if (validator.afterValidationOf(playerId).preparedErrorResponseIsNotEmpty())
            return validator.getErrorResponse();
        return Response.ok().entity(dtoMapper.createDtoFrom(playerService.findByUniqueId(playerId))).build();
    }
}