package ec.com.jmgorduez.BowlingGame.domain.frames;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameWithThridRollTest {

    private FinalFrameWithThridRoll finalFrameWithThridRollUnderTest;

    @Test
    void pointsForScore() {
        finalFrameWithThridRollUnderTest = new FinalFrameWithThridRoll(TEN_PINFALLS, FIVE_PINFALLS, ONE_PINFALLS);
        Integer pointsForScoreExpected = TEN + FIVE + ONE;
        assertThat(finalFrameWithThridRollUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
        finalFrameWithThridRollUnderTest = new FinalFrameWithThridRoll(TEN_PINFALLS, TEN_PINFALLS, ZERO_PINFALL);
        pointsForScoreExpected = TEN + TEN + ZERO;
        assertThat(finalFrameWithThridRollUnderTest.pointsForScore())
                .isEqualTo(pointsForScoreExpected);
    }

    @Test
    void toStringTest() {
        finalFrameWithThridRollUnderTest
                = new FinalFrameWithThridRoll(TEN_PINFALLS, FOUR_PINFALLS, THREE_PINFALLS);
        assertThat(finalFrameWithThridRollUnderTest.toString())
                .isEqualTo(TEN_PINFALLS.toString().concat(TAB)
                        .concat(FOUR_PINFALLS.toString()).concat(TAB)
                        .concat(THREE_PINFALLS.toString()));
    }
}