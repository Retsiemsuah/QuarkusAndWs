package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class BowlingGame extends PanacheEntityBase{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    BowlingGameState gameState;

    @OneToMany(mappedBy="bowlingGame")
    private List<GameRun> registeredGameRuns = new ArrayList<>();

    int currentGameRun;

    public List<GameRun> getRegisteredGameRuns() {
        return registeredGameRuns;
    }

    public void setRegisteredGameRuns(List<GameRun> registeredGameRuns) {
        this.registeredGameRuns = registeredGameRuns;
    }

    public void addNewGameRun(GameRun newGameRun) {
        registeredGameRuns.add(newGameRun);
    }

    public UUID getId() {
        return id;
    }

    public void setState(BowlingGameState newState) {
        if(gameState!=BowlingGameState.FINISHED) {
            gameState = newState;
        }
    }

    public BowlingGameState getGameState() {
        return gameState;
    }

    public int getCurrentGameRun() {
        return currentGameRun;
    }

    public void setCurrentGameRun(int currentGameRun) {
        this.currentGameRun = currentGameRun;
    }
}