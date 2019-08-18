package ec.com.jmgorduez.BowlingGame.domain.abstractions.frames;

import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.FIVE_PINFALLS;
import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.TEN_PINFALLS;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import static org.mockito.MockitoAnnotations.initMocks;

class AbstractFrameWithFirstRollTest {

    private AbstractFrameWithFirstRoll abstractFrameWithFirstRollUnderTest;
    private List<Optional<IBowlingFrame>> bowlingFrames;

    private static AbstractFrameWithFirstRoll SECOND_ABSTRACT_FRAME;
    private static AbstractFrameWithFirstRoll THIRD_ABSTRACT_FRAME;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bowlingFrames = new ArrayList<>();
        abstractFrameWithFirstRollUnderTest
                = mockAbstractFrame(TEN_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(abstractFrameWithFirstRollUnderTest));
        SECOND_ABSTRACT_FRAME = mockAbstractFrame(FIVE_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(SECOND_ABSTRACT_FRAME));
        THIRD_ABSTRACT_FRAME = mockAbstractFrame(TEN_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(THIRD_ABSTRACT_FRAME));
    }

    private AbstractFrameWithFirstRoll mockAbstractFrame(PinfallsNumber pinfallsFirstRoll,
                                                         Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        return mock(AbstractFrameWithFirstRoll.class, withSettings()
                .useConstructor(pinfallsFirstRoll, getFrameAt)
                .defaultAnswer(InvocationOnMock::callRealMethod));
    }

    @Test
    void pinfallsFirstRoll() {
        assertThat(abstractFrameWithFirstRollUnderTest.pinfallsFirstRoll())
                .isEqualTo(TEN_PINFALLS);
        abstractFrameWithFirstRollUnderTest = SECOND_ABSTRACT_FRAME;
        assertThat(abstractFrameWithFirstRollUnderTest.pinfallsFirstRoll())
                .isEqualTo(FIVE_PINFALLS);
        abstractFrameWithFirstRollUnderTest = THIRD_ABSTRACT_FRAME;
        assertThat(abstractFrameWithFirstRollUnderTest.pinfallsFirstRoll())
                .isEqualTo(TEN_PINFALLS);
    }

    @Test
    void nextFrame() {
        IBowlingFrame currentFrame =
                abstractFrameWithFirstRollUnderTest.nextFrame().get();
        assertThat(currentFrame)
                .isEqualToComparingFieldByField(SECOND_ABSTRACT_FRAME);
        currentFrame = currentFrame.nextFrame().get();
        assertThat(currentFrame)
                .isEqualToComparingFieldByField(THIRD_ABSTRACT_FRAME);
        assertThatThrownBy(currentFrame::nextFrame)
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void extraSimpleBonus() {
        assertThat(abstractFrameWithFirstRollUnderTest.extraSimpleBonus())
                .isEqualTo(TEN);
        abstractFrameWithFirstRollUnderTest = SECOND_ABSTRACT_FRAME;
        assertThat(abstractFrameWithFirstRollUnderTest.extraSimpleBonus())
                .isEqualTo(FIVE);
    }

    @Test
    void setFrameAtFunction(){
        Function<IBowlingFrame, Optional<IBowlingFrame>> frameAt = this::next;
        abstractFrameWithFirstRollUnderTest.setNextFrameFunction(frameAt);
        assertThat(abstractFrameWithFirstRollUnderTest.getNextFrameFunction)
                .isEqualTo(frameAt);
    }

    private Optional<IBowlingFrame> next(IBowlingFrame frame){
        Integer indexOfNextFrame = bowlingFrames.indexOf(ofNullable(frame)) + ONE;
        return bowlingFrames.get(indexOfNextFrame);
    }
}