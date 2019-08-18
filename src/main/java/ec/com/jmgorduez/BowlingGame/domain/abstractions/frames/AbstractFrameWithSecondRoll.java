package ec.com.jmgorduez.BowlingGame.domain.abstractions.frames;

import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class AbstractFrameWithSecondRoll
        extends AbstractFrameWithFirstRoll {

    protected final PinfallsNumber pinfallsSecondRoll;

    public AbstractFrameWithSecondRoll(PinfallsNumber pinfallsFirstRoll,
                                       PinfallsNumber pinfallsSecondRoll,
                                       Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        super(pinfallsFirstRoll, getFrameAt);
        this.pinfallsSecondRoll = pinfallsSecondRoll;
    }

    public AbstractFrameWithSecondRoll(PinfallsNumber pinfallsFirstRoll,
                                       PinfallsNumber pinfallsSecondRoll) {
        super(pinfallsFirstRoll);
        this.pinfallsSecondRoll = pinfallsSecondRoll;
    }

    @Override
    public Integer extraDoubleBonus(BiFunction<Integer, Integer, Integer> calculateDoubleExtraBonus) {
        return calculateDoubleExtraBonus.apply(pinfallsFirstRoll.getValue(), pinfallsSecondRoll.getValue());
    }
}
