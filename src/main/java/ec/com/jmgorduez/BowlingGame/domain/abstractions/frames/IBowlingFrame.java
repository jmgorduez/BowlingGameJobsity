package ec.com.jmgorduez.BowlingGame.domain.abstractions.frames;

import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface IBowlingFrame {

    Integer pointsForScore();

    PinfallsNumber pinfallsFirstRoll();

    Integer extraDoubleBonus(BiFunction<Integer, Integer, Integer> calculateDoubleExtraBonus);

    Integer extraSimpleBonus();

    Optional<IBowlingFrame> nextFrame();

    void setNextFrameFunction(Function<IBowlingFrame, Optional<IBowlingFrame>> getNextFrameFunction);
}
