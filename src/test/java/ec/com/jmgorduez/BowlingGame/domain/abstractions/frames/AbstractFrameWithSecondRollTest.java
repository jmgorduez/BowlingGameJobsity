package ec.com.jmgorduez.BowlingGame.domain.abstractions.frames;

import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.EIGHT;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.SIX;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;
import static org.mockito.MockitoAnnotations.initMocks;

class AbstractFrameWithSecondRollTest {

    private AbstractFrameWithSecondRoll abstractFrameWithSecondRollUnderTest;
    private List<Optional<IBowlingFrame>> bowlingFrames;

    private static AbstractFrameWithSecondRoll SECOND_ABSTRACT_FRAME;

    @BeforeEach
    void setUp() {
        initMocks(this);
        bowlingFrames = new ArrayList<>();
        abstractFrameWithSecondRollUnderTest = mockAbstractFrame(SIX_PINFALLS, ZERO_PINFALL, this::next);
        bowlingFrames.add(ofNullable(abstractFrameWithSecondRollUnderTest));
        SECOND_ABSTRACT_FRAME = mockAbstractFrame(SEVEN_PINFALLS, ONE_PINFALLS, this::next);
        bowlingFrames.add(ofNullable(SECOND_ABSTRACT_FRAME));
    }

    private AbstractFrameWithSecondRoll mockAbstractFrame(PinfallsNumber pinfallsFirstRoll,
                                                          PinfallsNumber pinfallsSecondRoll,
                                                          Function<IBowlingFrame, Optional<IBowlingFrame>> getFrameAt) {
        return mock(AbstractFrameWithSecondRoll.class, withSettings()
                .useConstructor(pinfallsFirstRoll, pinfallsSecondRoll, getFrameAt)
                .defaultAnswer(InvocationOnMock::callRealMethod));
    }

    @Test
    void extraDoubleBonus() {
        assertThat(abstractFrameWithSecondRollUnderTest.extraDoubleBonus(Math::addExact))
                .isEqualTo(SIX);
        abstractFrameWithSecondRollUnderTest = SECOND_ABSTRACT_FRAME;
        assertThat(abstractFrameWithSecondRollUnderTest.extraDoubleBonus(Math::addExact))
                .isEqualTo(EIGHT);
    }

    private Optional<IBowlingFrame> next(IBowlingFrame frame) {
        return bowlingFrames.get(bowlingFrames.indexOf(frame));
    }
}