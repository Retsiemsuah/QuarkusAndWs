package de.scheffler.data.api.bowling.game;

import de.scheffler.data.api.player.LocalPlayer;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameRun extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private LocalPlayer player;

    @OneToMany(mappedBy = "belongingToRun")
    @OrderBy("framePosition ASC")
    private List<Frame> frameHistory = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    BowlingGame bowlingGame;

    private int runNumberWithinGame;
    boolean gameRunFinished;

    public GameRun(LocalPlayer player, int runNumberWithinGame) {
        this.player = player;
        this.runNumberWithinGame=runNumberWithinGame;
    }

    public GameRun() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalPlayer getPlayer() {
        return player;
    }

    public void setPlayer(LocalPlayer player) {
        this.player = player;
    }

    public List<Frame> getFrameHistory() {
        return frameHistory;
    }

    public void setFrameHistory(List<Frame> throwHistoryList) {
        this.frameHistory = throwHistoryList;
    }

    public GameRun(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public BowlingGame getBowlingGame() {
        return bowlingGame;
    }

    public void setBowlingGame(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public int getRunNumberWithinGame() {
        return runNumberWithinGame;
    }

    public void setRunNumberWithinGame(int runNumberWithinGame) {
        this.runNumberWithinGame = runNumberWithinGame;
    }

    public boolean isGameRunFinished() {
        return gameRunFinished;
    }

    public void setGameRunFinished(boolean gameRunFinished) {
        this.gameRunFinished = gameRunFinished;
    }
}
