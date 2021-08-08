package de.scheffler.data.api.bowling.game;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Frame extends PanacheEntityBase {

    @Id
    @GeneratedValue
    long id;

    int framePosition;
    Integer firstThrow;
    boolean strike;
    Integer secondThrow;
    boolean spare;
    Integer thirdThrow;
    Integer totalScore;
    boolean isLastFrameWithinRun;
    boolean frameIsFinished;
    boolean scoreIsCalculated;

    @ManyToOne(fetch = FetchType.LAZY)
    GameRun belongingToRun;

    public Frame() {
    }

    public Frame(GameRun newRun) {
    }

    public int getFramePosition() {
        return framePosition;
    }

    public Frame setFramePosition(int framePosition) {
        this.framePosition = framePosition;
        return this;
    }

    public GameRun getBelongingToRun() {
        return belongingToRun;
    }

    public Frame setBelongingToRun(GameRun belongingToRun) {
        this.belongingToRun = belongingToRun;
        return this;
    }

    public Integer getFirstThrow() {
        return firstThrow;
    }

    public void setFirstThrow(int firstThrow) {
        this.firstThrow = firstThrow;
    }

    public Integer getSecondThrow() {
        return secondThrow;
    }

    public void setSecondThrow(int secondThrow) {
        this.secondThrow = secondThrow;
    }

    public Integer getThirdThrow() {
        return thirdThrow;
    }

    public void setThirdThrow(int thirdThrow) {
        this.thirdThrow = thirdThrow;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public boolean isScoreIsCalculated() {
        return scoreIsCalculated;
    }

    public void setScoreIsCalculated(boolean scoreIsCalculated) {
        this.scoreIsCalculated = scoreIsCalculated;
    }

    public boolean isFrameIsFinished() {
        return frameIsFinished;
    }

    public void setFrameIsFinished(boolean frameIsFinished) {
        this.frameIsFinished = frameIsFinished;
    }

    public boolean isIsLastFrameWithinRun() {
        return isLastFrameWithinRun;
    }

    public Frame setIsLastFrameWithinRun(boolean isLastFrameWithinRun) {
        this.isLastFrameWithinRun = isLastFrameWithinRun;
        return this;
    }

    public boolean isStrike() {
        return strike;
    }

    public Frame setStrike(boolean strike) {
        this.strike = strike;
        return this;
    }

    public boolean isSpare() {
        return spare;
    }

    public Frame setSpare(boolean spare) {
        this.spare = spare;
        return this;
    }
}
