package de.scheffler.logic.impl.bowling.game;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.FrameRepository;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.GameRunRepository;
import de.scheffler.logic.api.bowling.game.FrameService;
import de.scheffler.logic.api.bowling.game.GameRunService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GameRunServiceImpl implements GameRunService {

    @Inject
    GameRunRepository gameRunRepository;

    @Inject
    FrameService frameService;

    @Inject
    FrameRepository frameRepository;

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
        List<Frame> frameHistory = frameRepository.findBy(gameRun);
        for (int currFrameIndex = 0; currFrameIndex <= frameHistory.size(); currFrameIndex++) {
            Frame currentFrame = frameHistory.get(currFrameIndex);
            if (!currentFrame.isFrameIsFinished())
                return;

            int calculatedScoreFromPrevFrame = getCalculatedScoreFromPrevFrame(frameHistory, currFrameIndex);
            if (currentFrame.isIsLastFrameWithinRun()) {
                currentFrame.setTotalScore(calculateFinalScore(calculatedScoreFromPrevFrame, currentFrame));
                currentFrame.setScoreIsCalculated(true);
                frameService.save(currentFrame);
                return;
            }
            if (noStrikeOrSpareIsPresent(currentFrame)) {
                calculatedScoreFromPrevFrame = calculatedScoreFromPrevFrame + currentFrame.getFirstThrow() + currentFrame.getSecondThrow();
                currentFrame.setTotalScore(calculatedScoreFromPrevFrame);
                currentFrame.setScoreIsCalculated(true);
                frameService.save(currentFrame);
                continue;
            }
            Frame nextFrame = frameHistory.get(currFrameIndex + 1);
            if (!nextFrame.isFrameIsFinished()) {
                return;
            }
            calculatedScoreFromPrevFrame = calculateScoreFromInformationWithStrikeOrSpare(calculatedScoreFromPrevFrame, currentFrame, nextFrame);
            currentFrame.setTotalScore(calculatedScoreFromPrevFrame);
            currentFrame.setScoreIsCalculated(true);
            frameService.save(currentFrame);
        }
    }

    private int getCalculatedScoreFromPrevFrame(List<Frame> frameHistory, int currFrameIndex) {
        if (currFrameIndex > 0) {
            Frame previousFrame = frameHistory.get(currFrameIndex - 1);
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
        int score = alreadyAchievedPoints +
                escapeNull(currentFrame.getFirstThrow()) +
                escapeNull(currentFrame.getSecondThrow());
        if (currentFrame.isStrike()) {
            return score + escapeNull(nextFrame.getFirstThrow()) + escapeNull(nextFrame.getSecondThrow());
        }
        if (currentFrame.isSpare()) {
            return score + escapeNull(nextFrame.getFirstThrow());
        }
        return score;
    }

    private int escapeNull(Integer value) {
        return value == null ? 0 : value.intValue();
    }
}