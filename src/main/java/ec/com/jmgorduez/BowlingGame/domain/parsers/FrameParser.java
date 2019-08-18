package ec.com.jmgorduez.BowlingGame.domain.parsers;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.IFrameParser;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;
import ec.com.jmgorduez.BowlingGame.domain.frames.FinalFrameWithThridRoll;
import ec.com.jmgorduez.BowlingGame.domain.frames.NormalFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.SpareFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.StrikeFrame;

import java.util.Arrays;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public class FrameParser implements IFrameParser {

    @Override
    public IBowlingFrame parse(String frameValues)
            throws IllegalArgumentException {

        PinfallsNumber[] intFrameValues = toIntegerArray(frameValues);
        if (areInvalidValues(intFrameValues)) {
            throw new IllegalArgumentException();
        }
        if (areStrikeFrameValues(intFrameValues)) {
            return new StrikeFrame();
        }
        if (areFinalFrameValues(intFrameValues)) {
            return new FinalFrameWithThridRoll(intFrameValues[ZERO], intFrameValues[ONE], intFrameValues[TWO]);
        }
        if (areSpareFrameValues(intFrameValues)) {
            return new SpareFrame(intFrameValues[ZERO]);
        }
        return new NormalFrame(intFrameValues[ZERO], intFrameValues[ONE]);
    }

    private PinfallsNumber[] toIntegerArray(String value) {
        return Arrays.stream(value.split(BLANK_SPACE))
                .map(PinfallsNumber::parse)
                .toArray(PinfallsNumber[]::new);
    }

    private boolean areFinalFrameValues(PinfallsNumber[] frameValues) {
        return frameValues.length == THREE;
    }

    private boolean areSpareFrameValues(PinfallsNumber[] frameValues) {
        return Arrays.stream(frameValues).mapToInt(PinfallsNumber::getValue).sum() == TEN;
    }

    private boolean areStrikeFrameValues(PinfallsNumber[] frameValues) {
        return frameValues.length == ONE;
    }

    private boolean areInvalidValues(PinfallsNumber[] frameValues) {
        return Arrays.asList(frameValues).isEmpty() || frameValues.length > THREE;
    }
}
