package de.scheffler.logic.api.bowling.game.validation;

import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

public interface BowlingGameEndpointValidatorProvider {
    ResponseEndpointValidator newFindByIdValidator();

    ResponseEndpointValidator addNewPlayerValidator();

    ResponseEndpointValidator newStartGameValidator();

    ResponseEndpointValidator newAddThrowToGameValidator();
}
