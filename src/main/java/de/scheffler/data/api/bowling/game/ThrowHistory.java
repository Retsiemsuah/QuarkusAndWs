package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class ThrowHistory extends PanacheEntity {

    int firstThrow;
    int secondThrow;

    @ManyToOne
    GameRun belongingToRun;

    public ThrowHistory() {
    }

    public ThrowHistory(GameRun newRun) {
    }
}
