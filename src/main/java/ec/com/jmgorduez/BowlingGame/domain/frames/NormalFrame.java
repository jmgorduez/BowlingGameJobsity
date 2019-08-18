package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.AbstractFrameWithSecondRoll;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.Function;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.TAB;

public class NormalFrame extends AbstractFrameWithSecondRoll {

    public NormalFrame(PinfallsNumber pinfallsFirstRoll,
                       PinfallsNumber pinfallsSecondRoll,
                       Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        super(pinfallsFirstRoll, pinfallsSecondRoll, getFrameAt);
    }

    public NormalFrame(PinfallsNumber pinfallsFirstRoll,
                       PinfallsNumber pinfallsSecondRoll) {
        super(pinfallsFirstRoll, pinfallsSecondRoll);
    }

    @Override
    public Integer pointsForScore() {
        return pinfallsFirstRoll.getValue() + pinfallsSecondRoll.getValue();
    }

    @Override
    public String toString(){
        return pinfallsFirstRoll.toString().concat(TAB)
                .concat(pinfallsSecondRoll.toString());
    }
}
