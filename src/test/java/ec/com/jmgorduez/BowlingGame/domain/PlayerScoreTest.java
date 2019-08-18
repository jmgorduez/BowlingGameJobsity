package ec.com.jmgorduez.BowlingGame.domain;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.AbstractFrameWithFirstRoll;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.NormalFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.SpareFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.StrikeFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.JOHN;
import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.FIVE_PINFALLS;
import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.ONE_PINFALLS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PlayerScoreTest {

    private PlayerScore playerScoreUnderTest;
    private StrikeFrame STRIKE_FRAME;
    private SpareFrame SPARE_FRAME;
    private NormalFrame NORMAL_FRAME;

    @BeforeEach
    void setUp() {
        STRIKE_FRAME = new StrikeFrame();
        SPARE_FRAME = new SpareFrame(FIVE_PINFALLS);
        NORMAL_FRAME = new NormalFrame(FIVE_PINFALLS, ONE_PINFALLS);
        playerScoreUnderTest = new PlayerScore.PlayerScoreBuilder(JOHN).build();
    }

    @Test
    void frames() {
        assertThat(playerScoreUnderTest.frames().isEmpty())
                .isTrue();
        playerScoreUnderTest = new PlayerScore.PlayerScoreBuilder(JOHN)
                .addFrame(STRIKE_FRAME)
                .addFrame(SPARE_FRAME)
                .addFrame(NORMAL_FRAME)
                .build();
        assertThat(playerScoreUnderTest.frames().size())
                .isEqualTo(THREE);
    }

    @Test
    void addFrame() {
        PlayerScore.PlayerScoreBuilder builder = new PlayerScore.PlayerScoreBuilder(JOHN)
                .addFrame(new StrikeFrame());
        playerScoreUnderTest = builder.build();
        assertThat(playerScoreUnderTest.frames.size())
                .isEqualTo(ONE);

        builder.addFrame(new StrikeFrame());
        playerScoreUnderTest = builder.build();
        assertThat(builder.getFrames().size())
                .isEqualTo(TWO);
    }

    @Test
    void addFrameVerifyUpdateFrameAtFunction() {
        IBowlingFrame abstractBowlingFrameMock = mock(AbstractFrameWithFirstRoll.class);
        new PlayerScore.PlayerScoreBuilder(JOHN).addFrame(abstractBowlingFrameMock);
        verify(abstractBowlingFrameMock, times(ONE)).setNextFrameFunction(any());
    }

    @Test
    void playerName() {
        assertThat(playerScoreUnderTest.getPlayerName())
                .isEqualTo(JOHN);
    }

    @Test
    void nextFrameOf() {
        playerScoreUnderTest = new PlayerScore.PlayerScoreBuilder(JOHN)
                .addFrame(new StrikeFrame())
                .addFrame(new SpareFrame(FIVE_PINFALLS))
                .build();
        assertThat(playerScoreUnderTest.nextFrameOf(playerScoreUnderTest.frames.get(ZERO).get()))
                .isEqualTo(playerScoreUnderTest.frames.get(ONE));
    }

    @Test
    void accumulatedScoreAt() {
        playerScoreUnderTest = new PlayerScore.PlayerScoreBuilder(JOHN)
                .addFrame(STRIKE_FRAME)
                .addFrame(SPARE_FRAME)
                .addFrame(NORMAL_FRAME)
                .build();
        Integer accumulatedScoreExpected = STRIKE_FRAME.pointsForScore();
        assertThat(playerScoreUnderTest.accumulatedScoreAt(STRIKE_FRAME))
                .isEqualTo(accumulatedScoreExpected);
        accumulatedScoreExpected += SPARE_FRAME.pointsForScore();
        assertThat(playerScoreUnderTest.accumulatedScoreAt(SPARE_FRAME))
                .isEqualTo(accumulatedScoreExpected);
        accumulatedScoreExpected += NORMAL_FRAME.pointsForScore();
        assertThat(playerScoreUnderTest.accumulatedScoreAt(NORMAL_FRAME))
                .isEqualTo(accumulatedScoreExpected);
    }
}