package de.scheffler.logic.impl.player.validation;

import de.scheffler.endpoint.api.player.dto.PlayerDto;
import de.scheffler.logic.api.player.validation.PlayerEndpointValidatorProvider;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

@ApplicationScoped
public class PlayerEndpointValidatorProviderImpl implements PlayerEndpointValidatorProvider {

    @Inject
    PlayerEndpointCreateNewPlayerValidator gameStartGameValidator;

    @Inject
    PlayerEndpointFindByIdValidator findByIdValidator;

    @Override
    public ResponseEndpointValidator newCreateNewPlayerValidator() {
        return gameStartGameValidator.withExpectedParameterCountOf(1).ofClasses(new LinkedList<>(Arrays.asList(PlayerDto.class))).allNotNullable().build();
    }

    @Override
    public ResponseEndpointValidator newFindByIdValidator() {
        return findByIdValidator.withExpectedParameterCountOf(1).ofClasses(new LinkedList<>(Arrays.asList(UUID.class))).allNotNullable().build();
    }

}
