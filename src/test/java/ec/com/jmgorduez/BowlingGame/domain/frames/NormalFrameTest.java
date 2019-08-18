package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private NormalFrame normalFrameUnderTest;
    private List<Optional<IBowlingFrame>> bowlingFrames;

    private static NormalFrame SECOND_NORMAL_FRAME;
    private static NormalFrame THIRD_NORMAL_FRAME;

    @BeforeEach
    void setUp() {
        bowlingFrames = new ArrayList<>();
        normalFrameUnderTest = new NormalFrame(SIX_PINFALLS, ZERO_PINFALL, this::next);
        bowlingFrames.add(ofNullable(normalFrameUnderTest));
        SECOND_NORMAL_FRAME = new NormalFrame( SEVEN_PINFALLS, ONE_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(SECOND_NORMAL_FRAME));
        THIRD_NORMAL_FRAME = new NormalFrame( EIGHT_PINFALLS, ZERO_PINFALL, this::next);
        bowlingFrames.add(ofNullable(THIRD_NORMAL_FRAME));
    }

    @Test
    void pointsForScore() {
        Integer pointsForScoreExpected = SIX + ZERO;
        assertThat(normalFrameUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
        normalFrameUnderTest = SECOND_NORMAL_FRAME;
        pointsForScoreExpected = SEVEN + ONE;
        assertThat(normalFrameUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
    }

    @Test
    void toStringTest(){
        assertThat(normalFrameUnderTest.toString())
                .isEqualTo(SIX_PINFALLS.toString().concat(TAB).concat(ZERO_PINFALL.toString()));
    }

    private Optional<IBowlingFrame> next(IBowlingFrame frame){
        Integer indexOfNextFrame = bowlingFrames.indexOf(ofNullable(frame)) + ONE;
        return bowlingFrames.get(indexOfNextFrame);
    }
}