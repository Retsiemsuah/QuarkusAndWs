package de.scheffler.logic.api.bowling.game.validation;

import de.scheffler.logic.impl.bowling.game.validation.BowlingGameFindByGameIdValidator;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

public interface BowlingGameEndpointValidatorProvider {
    BowlingGameFindByGameIdValidator newFindByIdValidator();

    ResponseEndpointValidator addNewPlayerValidator();

    ResponseEndpointValidator newStartGameValidator();

    ResponseEndpointValidator newAddThrowToGameValidator();
}
