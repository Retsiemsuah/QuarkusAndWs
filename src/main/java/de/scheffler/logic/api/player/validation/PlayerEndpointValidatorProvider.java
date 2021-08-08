package de.scheffler.logic.api.player.validation;

import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

public interface PlayerEndpointValidatorProvider {

    ResponseEndpointValidator newCreateNewPlayerValidator();

    ResponseEndpointValidator newFindByIdValidator();

}