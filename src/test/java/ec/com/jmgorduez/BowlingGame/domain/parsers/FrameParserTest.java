package ec.com.jmgorduez.BowlingGame.domain.parsers;

import ec.com.jmgorduez.BowlingGame.domain.frames.FinalFrameWithThridRoll;
import ec.com.jmgorduez.BowlingGame.domain.frames.NormalFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.SpareFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.StrikeFrame;
import ec.com.jmgorduez.BowlingGame.domain.parsers.FrameParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.BLANK_SPACE;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.X;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.*;
import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameParserTest {

    private FrameParser frameParserUnderTest;

    @BeforeEach
    void setUp() {
        frameParserUnderTest = new FrameParser();
    }

    @Test
    void parse() {
        assertThat(frameParserUnderTest.parse(NORMAL_FRAME_STRING_7_0))
                .isEqualToComparingFieldByField(new NormalFrame(SEVEN_PINFALLS, ZERO_PINFALL));
        assertThat(frameParserUnderTest.parse(STRIKE_FRAME_STRING))
                .isEqualToComparingFieldByField(new StrikeFrame());
        assertThat(frameParserUnderTest.parse(SPARE_FRAME_STRING_8_2))
                .isEqualToComparingFieldByField(new SpareFrame(EIGHT_PINFALLS));
        assertThat(frameParserUnderTest.parse(FINAL_FRAME_STRING_8_1_0))
                .isEqualToComparingFieldByField(new FinalFrameWithThridRoll(EIGHT_PINFALLS, ONE_PINFALLS, ZERO_PINFALL));
        assertThat(frameParserUnderTest.parse(X))
                .isEqualToComparingFieldByField(new StrikeFrame());
        assertThatThrownBy(() -> frameParserUnderTest.parse(BLANK_SPACE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}