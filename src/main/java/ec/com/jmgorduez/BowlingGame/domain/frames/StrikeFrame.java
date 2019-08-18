package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.AbstractFrameWithFirstRoll;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public class StrikeFrame extends AbstractFrameWithFirstRoll {

    public StrikeFrame(Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        super(PinfallsNumber.valueOf(TEN).get(), getFrameAt);
    }

    public StrikeFrame() {
        super(PinfallsNumber.valueOf(TEN).get());
    }

    @Override
    public Integer pointsForScore() {
        return pinfallsFirstRoll.getValue() + nextFrame().get().extraDoubleBonus(Math::addExact);
    }

    @Override
    public Integer extraDoubleBonus(BiFunction<Integer, Integer, Integer> calculateDoubleExtraBonus) {
        return calculateDoubleExtraBonus.apply(pinfallsFirstRoll.getValue(), nextFrame().get().pinfallsFirstRoll().getValue());
    }

    @Override
    public String toString() {
        return TAB.concat(X);
    }
}
