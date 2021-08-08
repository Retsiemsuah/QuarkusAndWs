package de.scheffler.logic.impl.bowling.game;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import de.scheffler.logic.api.bowling.game.FrameService;
import de.scheffler.logic.api.bowling.game.GameRunService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class GameRunServiceImpl implements GameRunService {

    @Inject
    GameRunRepository gameRunRepository;

    @Inject
    FrameService frameService;

    @Override
    @Transactional
    public void addThrowToGameRunAndCalculateScores(GameRun run, int throwValue) {
        Frame frameAfterAddingThrow = frameService.addThrowToCurrentFrame(run, throwValue);
        if (frameAfterAddingThrow.isFrameIsFinished()) {
            if (frameAfterAddingThrow.isIsLastFrameWithinRun()) {
                run.setGameRunFinished(true);
            }
            calculateFrameScores(run);
        }
        gameRunRepository.persist(run);
    }

    @Override
    @Transactional
    public void calculateFrameScores(GameRun gameRun) {
        for (int currFrameIndex = 0; currFrameIndex <= gameRun.getFrameHistory().size(); currFrameIndex++) {
            Frame currentFrame = gameRun.getFrameHistory().get(currFrameIndex);
            if (!currentFrame.isFrameIsFinished())
                return;

            int calculatedScoreFromPrevFrame = getCalculatedScoreFromPrevFrame(gameRun, currFrameIndex);
            if (currentFrame.isIsLastFrameWithinRun()) {
                currentFrame.setTotalScore(calculateFinalScore(calculatedScoreFromPrevFrame, currentFrame));
                return;
            }
            if (noStrikeOrSpareIsPresent(currentFrame)) {
                calculatedScoreFromPrevFrame = calculatedScoreFromPrevFrame + currentFrame.getFirstThrow() + currentFrame.getSecondThrow();
                currentFrame.setTotalScore(calculatedScoreFromPrevFrame);
                frameService.save(currentFrame);
                continue;
            }
            Frame nextFrame = gameRun.getFrameHistory().get(currFrameIndex + 1);
            if (!nextFrame.isFrameIsFinished()) {
                return;
            }
            calculatedScoreFromPrevFrame = calculateScoreFromInformationWithStrikeOrSpare(calculatedScoreFromPrevFrame, currentFrame, nextFrame);
            currentFrame.setTotalScore(calculatedScoreFromPrevFrame);
            frameService.save(currentFrame);
        }
    }

    private int getCalculatedScoreFromPrevFrame(GameRun gameRun, int currFrameIndex) {
        if (currFrameIndex > 0) {
            Frame previousFrame = gameRun.getFrameHistory().get(currFrameIndex - 1);
            return previousFrame.isScoreIsCalculated() ? previousFrame.getTotalScore() : 0;
        }
        return 0;
    }

    private boolean noStrikeOrSpareIsPresent(Frame currentFrame) {
        return !currentFrame.isStrike() && !currentFrame.isSpare();
    }

    private Integer calculateFinalScore(int alreadyCalculatedScore, Frame currentFrame) {
        if (currentFrame.isStrike()) {
            if (currentFrame.getSecondThrow() == 10) {
                return alreadyCalculatedScore + currentFrame.getFirstThrow() + (2 * currentFrame.getSecondThrow()) + (3 * currentFrame.getThirdThrow());
            }
            return alreadyCalculatedScore + currentFrame.getFirstThrow() + (2 * currentFrame.getSecondThrow()) + (3 * currentFrame.getThirdThrow());
        }
        if (currentFrame.isSpare()) {
            return alreadyCalculatedScore + currentFrame.getFirstThrow() + currentFrame.getSecondThrow() + (2 * currentFrame.getThirdThrow());
        }
        return alreadyCalculatedScore + currentFrame.getFirstThrow() + currentFrame.getSecondThrow();
    }

    private int calculateScoreFromInformationWithStrikeOrSpare(int alreadyAchievedPoints, Frame currentFrame, Frame nextFrame) {
        int score = alreadyAchievedPoints + currentFrame.getFirstThrow() + currentFrame.getSecondThrow();
        if (currentFrame.isStrike()) {
            return score + nextFrame.getFirstThrow() + nextFrame.getSecondThrow();
        }
        if (currentFrame.isSpare()) {
            return score + nextFrame.getFirstThrow();
        }
        return score;
    }
}