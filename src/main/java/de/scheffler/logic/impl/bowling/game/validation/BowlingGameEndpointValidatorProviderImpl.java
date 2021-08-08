package de.scheffler.logic.impl.bowling.game.validation;

import de.scheffler.logic.api.bowling.game.validation.BowlingGameEndpointValidatorProvider;
import de.scheffler.logic.impl.common.validation.ResponseEndpointValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BowlingGameEndpointValidatorProviderImpl implements BowlingGameEndpointValidatorProvider {

    @Inject
    BowlingGameFindByGameIdValidator findByGameValidator;

    @Inject
    BowlingGameAddNewPlayerValidator addNewPlayerValidator;

    @Inject
    BowlingGameStartGameValidator gameStartGameValidator;

    @Inject
    BowlingGameAddThrowToGameValidator addThrowValidator;

    @Override
    public ResponseEndpointValidator newFindByIdValidator() {
        return findByGameValidator.withExpectedParameterCountOf(1).ofClasses(new LinkedList<>(List.of(UUID.class))).allNotNullable().build();
    }

    @Override
    public ResponseEndpointValidator addNewPlayerValidator() {
        return addNewPlayerValidator.withExpectedParameterCountOf(2).ofClasses(new LinkedList<>(Arrays.asList(UUID.class,UUID.class))).allNotNullable().build();
    }

    @Override
    public ResponseEndpointValidator newStartGameValidator() {
        return gameStartGameValidator.withExpectedParameterCountOf(1).ofClasses(new LinkedList<>(List.of(UUID.class))).allNotNullable().build();
    }

    @Override
    public ResponseEndpointValidator newAddThrowToGameValidator() {
        return addThrowValidator.withExpectedParameterCountOf(2).ofClasses(new LinkedList<>(Arrays.asList(Integer.class, UUID.class))).allNotNullable().build();
    }
}