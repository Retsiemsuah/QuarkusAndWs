package de.scheffler.logic.impl.bowling.game;

import de.scheffler.data.api.bowling.game.Frame;
import de.scheffler.data.api.bowling.game.FrameRepository;
import de.scheffler.data.api.bowling.game.GameRun;
import de.scheffler.logic.api.bowling.game.FrameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class FrameServiceImpl implements FrameService {

    @Inject
    FrameRepository frameRepository;

    @Override
    @Transactional
    public Frame addThrowToCurrentFrame(GameRun runToAddTo, int throwValue) {
        Frame frameToAddThrow =frameRepository.findCurrentFrameFor(runToAddTo);
        addThrowToFrame(frameToAddThrow,throwValue);
        return frameToAddThrow;
    }

    @Override
    @Transactional
    public Frame addThrowToFrame(Frame frameToAddThrow, int throwValue) {
        if(frameToAddThrow==null||throwValue<0||throwValue>10)
            return null;
        if(frameToAddThrow.getFirstThrow()==null) {
            setValuesForFirstThrow(frameToAddThrow, throwValue);
            return frameToAddThrow;
        }
        if(frameToAddThrow.getSecondThrow()==null) {
            setValuesForSecondThrow(frameToAddThrow,throwValue);
            return frameToAddThrow;
        }
        if(frameToAddThrow.isIsLastFrameWithinRun()
                && frameToAddThrow.getThirdThrow()==null
                && (frameToAddThrow.isSpare()||frameToAddThrow.isStrike())){
            frameToAddThrow.setThirdThrow(throwValue);
            frameToAddThrow.setFrameIsFinished(true);
            frameRepository.persist(frameToAddThrow);
        }
        return frameToAddThrow;
    }

    private void setValuesForSecondThrow(Frame frameToAddThrow, int throwValue) {
        frameToAddThrow.setFirstThrow(throwValue);
        if(!frameToAddThrow.isIsLastFrameWithinRun())
            frameToAddThrow.setFrameIsFinished(true);
        if(containsSpare(frameToAddThrow))
            frameToAddThrow.setSpare(true);
        frameRepository.persist(frameToAddThrow);
    }

    private void setValuesForFirstThrow(Frame frame, int throwValue) {
        frame.setFirstThrow(throwValue);
        if(containsStrike(frame))
            frame.setStrike(true);
        frameRepository.persist(frame);
    }

    @Override
    public boolean containsStrike(Frame frame){
        if(frame==null)
            return false;
        if(frame.getFirstThrow()==null)
            return false;
        return frame.getFirstThrow()==10;
    }

    @Override
    public boolean containsSpare(Frame frame){
        if(frame==null)
            return false;
        if(frame.getFirstThrow()==null||frame.getSecondThrow()==null)
            return false;
        if(frame.getFirstThrow()>=10)
            return false;
        return frame.getFirstThrow()+frame.getSecondThrow()==10;
    }

    @Override
    public void save(Frame frame) {
        frameRepository.persist(frame);
    }
}
