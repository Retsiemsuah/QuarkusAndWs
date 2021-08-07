package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class GameRun extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne
//    private Player player;
//
//    @OneToMany
//    private List<ThrowHistory> throwHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    BowlingGame bowlingGame;

    private int runNumberWithinGame;


//    public GameRun(Player newPlayer) {
//        player=newPlayer;
//    }

    public GameRun() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    //    public Player getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(Player player) {
//        this.player = player;
//    }
//
//    public List<ThrowHistory> getThrowHistoryList() {
//        return throwHistoryList;
//    }
//
//    public void setThrowHistoryList(List<ThrowHistory> throwHistoryList) {
//        this.throwHistoryList = throwHistoryList;
//    }
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
}
