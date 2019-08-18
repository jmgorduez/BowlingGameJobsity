package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.THIRTY;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.TWENTY;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

class StrikeFrameTest {

    private StrikeFrame strikeFrameUnderTest;
    private List<Optional<IBowlingFrame>> bowlingFrames;

    @BeforeEach
    void setUp() {
        bowlingFrames = new ArrayList<>();
        strikeFrameUnderTest = new StrikeFrame(this::next);
        bowlingFrames.add(ofNullable(strikeFrameUnderTest));
        bowlingFrames.add(ofNullable(new StrikeFrame(this::next)));
        bowlingFrames.add(ofNullable(new StrikeFrame(this::next)));
    }

    @Test
    void pointsForScore() {
        assertThat(strikeFrameUnderTest.pointsForScore())
                .isEqualTo(THIRTY);
    }

    @Test
    void extraDoubleBonus() {
        assertThat(strikeFrameUnderTest.extraDoubleBonus(Math::addExact))
                .isEqualTo(TWENTY);
    }

    @Test
    void toStringTest(){
        assertThat(strikeFrameUnderTest.toString())
                .isEqualTo(TAB.concat(X));
    }

    private Optional<IBowlingFrame> next(IBowlingFrame frame){
        Integer indexOfNextFrame = bowlingFrames.indexOf(ofNullable(frame)) + ONE;
        return bowlingFrames.get(indexOfNextFrame);
    }
}