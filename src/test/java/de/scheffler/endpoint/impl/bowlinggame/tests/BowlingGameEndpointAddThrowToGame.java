package de.scheffler.endpoint.impl.bowlinggame.tests;

import de.scheffler.endpoint.impl.bowlinggame.requestfactory.BowlingGameEndpointRequestResponseFactory;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class BowlingGameEndpointAddThrowToGame {

    @Inject
    BowlingGameEndpointRequestResponseFactory requestFactory;

    @Test
    void addThrowToGame(){
        assertTrue(true);
    }
}
