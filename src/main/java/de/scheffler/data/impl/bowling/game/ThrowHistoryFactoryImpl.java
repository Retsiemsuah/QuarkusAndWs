package de.scheffler.data.impl.bowling.game;

import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.FrameFactory;
import de.scheffler.data.api.bowling.game.FrameRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ThrowHistoryFactoryImpl implements FrameFactory {

    @Inject
    FrameRepository throwHistoryRepository;

    @ConfigProperty(name = "config.frame.factory.framesperrun")
    int maxNumberOfFrames;

    @Override
    public List<Frame> createEmptyFrame(GameRun newRun) {
        List<Frame> emptyHistoryList = new ArrayList<>();
        for(int framePosition = 0 ; framePosition < maxNumberOfFrames ; framePosition++){
            emptyHistoryList.add(createEmptyFrame(newRun, framePosition, framePosition==maxNumberOfFrames-1));
        }
        return emptyHistoryList;
    }

    @Override
    public Frame createEmptyFrame(GameRun newRun, int framePosition, boolean lastFrameInRun) {
        Frame newHist = new Frame()
                .setFramePosition(framePosition)
                .setBelongingToRun(newRun)
                .setIsLastFrameWithinRun(lastFrameInRun)
                .setStrike(false)
                .setSpare(false);
        throwHistoryRepository.persist(newHist);
        return newHist;
    }
}
