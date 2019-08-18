package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.AbstractFrameWithSecondRoll;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.Function;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public class SpareFrame extends AbstractFrameWithSecondRoll {

    public SpareFrame(PinfallsNumber pinfallsFirstRoll,
                      Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        super(pinfallsFirstRoll, PinfallsNumber.valueOf(TEN - pinfallsFirstRoll.getValue()).get(), getFrameAt);
    }

    public SpareFrame(PinfallsNumber pinfallsFirstRoll) {
        super(pinfallsFirstRoll, PinfallsNumber.valueOf(TEN - pinfallsFirstRoll.getValue()).get());
    }

    @Override
    public Integer pointsForScore() {
        return pinfallsFirstRoll.getValue() + pinfallsSecondRoll.getValue() + nextFrame().get().extraSimpleBonus();
    }

    @Override
    public String toString(){

        return pinfallsFirstRoll.toString().concat(TAB).concat(SLASH);
    }
}
