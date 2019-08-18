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

class SpareFrameTest {

    private SpareFrame spareFrameUnderTest;
    private List<Optional<IBowlingFrame>> bowlingFrames;

    private static SpareFrame SECOND_SPARE_FRAME;
    private static SpareFrame THIRD_SPARE_FRAME;


    @BeforeEach
    void setUp() {
        bowlingFrames = new ArrayList<>();
        spareFrameUnderTest = new SpareFrame( FOUR_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(spareFrameUnderTest));
        SECOND_SPARE_FRAME = new SpareFrame( FIVE_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(SECOND_SPARE_FRAME));
        THIRD_SPARE_FRAME = new SpareFrame( SIX_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(THIRD_SPARE_FRAME));
    }

    @Test
    void pointsForScore() {
        Integer pointsForScoreExpected = TEN + FIVE;
        assertThat(spareFrameUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
        spareFrameUnderTest = SECOND_SPARE_FRAME;
        pointsForScoreExpected = TEN + SIX;
        assertThat(spareFrameUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
    }

    @Test
    void toStringTest(){
        assertThat(spareFrameUnderTest.toString())
                .isEqualTo(FOUR_PINFALLS.toString().concat(TAB).concat(SLASH));
    }

    private Optional<IBowlingFrame> next(IBowlingFrame frame){
        Integer indexOfNextFrame = bowlingFrames.indexOf(ofNullable(frame)) + ONE;
        return bowlingFrames.get(indexOfNextFrame);
    }
}