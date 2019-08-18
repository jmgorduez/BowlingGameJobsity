package ec.com.jmgorduez.BowlingGame.domain.abstractions.frames;

import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.empty;

public abstract class AbstractFrameWithFirstRoll implements IBowlingFrame {

    protected final PinfallsNumber pinfallsFirstRoll;
    protected Function<IBowlingFrame, Optional<IBowlingFrame>> getNextFrameFunction;

    public AbstractFrameWithFirstRoll(PinfallsNumber pinfallsFirstRoll,
                                      Function<IBowlingFrame, Optional<IBowlingFrame>> getNextFrameFunction) {
        this.pinfallsFirstRoll = pinfallsFirstRoll;
        this.getNextFrameFunction = getNextFrameFunction;
    }

    public AbstractFrameWithFirstRoll(PinfallsNumber pinfallsFirstRoll) {
        this.pinfallsFirstRoll = pinfallsFirstRoll;
        this.getNextFrameFunction = frame -> empty();
    }

    @Override
    public PinfallsNumber pinfallsFirstRoll() {
        return pinfallsFirstRoll;
    }

    @Override
    public Optional<IBowlingFrame> nextFrame() {
        try {
            return getNextFrameFunction.apply(this);
        } catch (Throwable throwable) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Integer extraSimpleBonus(){
        return pinfallsFirstRoll.getValue();
    }

    @Override
    public void setNextFrameFunction(Function<IBowlingFrame, Optional<IBowlingFrame>> getNextFrameFunction){
        this.getNextFrameFunction = getNextFrameFunction;
    }
}
