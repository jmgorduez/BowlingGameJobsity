package ec.com.jmgorduez.BowlingGame.domain.readers;

import ec.com.jmgorduez.BowlingGame.domain.PlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.parsers.FrameParser;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IFrameParser;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IPlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.frames.FinalFrameWithThridRoll;
import ec.com.jmgorduez.BowlingGame.domain.frames.NormalFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.SpareFrame;
import ec.com.jmgorduez.BowlingGame.domain.frames.StrikeFrame;
import ec.com.jmgorduez.BowlingGame.domain.readers.BowlingGameScoreReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.*;
import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameScoreReaderTest {

    private BowlingGameScoreReader bowlingGameScoreReaderUnderTest;
    private IFrameParser frameParser;

    @BeforeEach
    void setUp() {
        bowlingGameScoreReaderUnderTest = new BowlingGameScoreReader();
        this.frameParser = new FrameParser();
    }

    @Test
    void readOnlyOneScore() {
        List<String> lines = generateLinesJohnScore();
        List<IPlayerScore> playerScores = bowlingGameScoreReaderUnderTest.readScores(lines, frameParser::parse);
        IPlayerScore playerScoreExpected = generatePlayerScore_John_3_7_6_3_10_8_1_10_10_9_0_7_3_4_4_10_9_0();
        playerScores.forEach(isEqualTo(playerScoreExpected));
    }

    @Test
    void readTwoScores() {
        List<String> lines = generateLinesJeffAndJohnScores();
        List<IPlayerScore> playerScores = bowlingGameScoreReaderUnderTest.readScores(lines, frameParser::parse);

        IPlayerScore johnScoreExpected = generatePlayerScore_John_3_7_6_3_10_8_1_10_10_9_0_7_3_4_4_10_9_0();
        playerScores.stream()
                .filter(this::isJohnScore)
                .forEach(isEqualTo(johnScoreExpected));

        IPlayerScore jeffScoreExpected = generatePlayerScore_Jeff_10_7_3_9_0_10_0_8_8_2_F_6_10_10_10_8_1();
        playerScores.stream()
                .filter(this::isJeffScore)
                .forEach(isEqualTo(jeffScoreExpected));
    }

    @Test
    void getReadFrameInvalidRoll() {
        List<String> lines = Arrays.asList(JEFF_INVALID_ROLL);
        assertThatThrownBy(() -> bowlingGameScoreReaderUnderTest.readScores(lines,frameParser::parse))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void getRollsByPlayerFrames() {
        List<String> lines = generateLinesJeffAndJohnScores();
        Queue<String> rollsByFrameExpected = generateLinesJeffRollsByFrames();
        bowlingGameScoreReaderUnderTest.getRollsByPlayerFrames(JEFF, lines)
                .forEach(rollByFrame -> assertThat(rollByFrame).isEqualTo(rollsByFrameExpected.poll()));
    }

    @Test
    void getRollsByPlayerFramesInvalidRoll() {
        List<String> lines = Arrays.asList(JEFF_INVALID_ROLL);
        assertThatThrownBy(() -> bowlingGameScoreReaderUnderTest.getRollsByPlayerFrames(JEFF, lines))
                .isInstanceOf(IllegalArgumentException.class);

    }

    private Queue<String> generateLinesJeffRollsByFrames() {
        return Arrays.asList(STRIKE_FRAME, SPARE_FRAME_7_3, NORMAL_FRAME_9_0, STRIKE_FRAME, NORMAL_FRAME_0_8,
                SPARE_FRAME_8_2, NORMAL_FRAME_F_6, STRIKE_FRAME, STRIKE_FRAME, FINAL_FRAME_10_8_1)
                .stream().collect(Collectors.toCollection(ArrayDeque::new));
    }

    private List<String> generateLinesJohnScore() {
        return Arrays.asList(JOHN_3, JOHN_7, JOHN_6, JOHN_3, JOHN_10, JOHN_8, JOHN_1,
                JOHN_10, JOHN_10, JOHN_9, JOHN_0, JOHN_7, JOHN_3, JOHN_4, JOHN_4, JOHN_10, JOHN_9, JOHN_0)
                .stream().collect(Collectors.toList());
    }

    private List<String> generateLinesJeffAndJohnScores() {
        return Arrays.asList(JEFF_10, JOHN_3, JOHN_7, JEFF_7, JEFF_3, JOHN_6, JOHN_3, JEFF_9, JEFF_0,
                JOHN_10, JEFF_10, JOHN_8, JOHN_1, JEFF_0, JEFF_8, JOHN_10, JEFF_8, JEFF_2, JOHN_10, JEFF_F, JEFF_6,
                JOHN_9, JOHN_0, JEFF_10, JOHN_7, JOHN_3, JEFF_10, JOHN_4, JOHN_4, JEFF_10, JEFF_8, JEFF_1,
                JOHN_10, JOHN_9, JOHN_0).stream().collect(Collectors.toList());
    }

    private Consumer<IPlayerScore> isEqualTo(IPlayerScore johnScoreExpected) {
        return playerScore -> {
            for (int j = 0; j < playerScore.frames().size(); j++) {
                assertThat(playerScore.frames().get(j))
                        .isEqualToIgnoringGivenFields(johnScoreExpected.frames().get(j), GET_NEXT_FRAME_FUNCTION);
            }
        };
    }

    private boolean isJohnScore(IPlayerScore playerScore) {
        return playerScore.getPlayerName().equals(JOHN);
    }

    private boolean isJeffScore(IPlayerScore playerScore) {
        return playerScore.getPlayerName().equals(JEFF);
    }

    private IPlayerScore generatePlayerScore_John_3_7_6_3_10_8_1_10_10_9_0_7_3_4_4_10_9_0() {
        PlayerScore.PlayerScoreBuilder builder = new PlayerScore.PlayerScoreBuilder(JOHN);
        Arrays.asList(new SpareFrame(THREE_PINFALLS),
                new NormalFrame(SIX_PINFALLS, THREE_PINFALLS),
                new StrikeFrame(),
                new NormalFrame(EIGHT_PINFALLS, ONE_PINFALLS),
                new StrikeFrame(),
                new StrikeFrame(),
                new NormalFrame(NINE_PINFALLS, ZERO_PINFALL),
                new SpareFrame(SEVEN_PINFALLS),
                new NormalFrame(FOUR_PINFALLS, FOUR_PINFALLS),
                new FinalFrameWithThridRoll(TEN_PINFALLS, NINE_PINFALLS, ZERO_PINFALL))
                .forEach(builder::addFrame);
        return builder.build();
    }

    private IPlayerScore generatePlayerScore_Jeff_10_7_3_9_0_10_0_8_8_2_F_6_10_10_10_8_1() {
        PlayerScore.PlayerScoreBuilder builder = new PlayerScore.PlayerScoreBuilder(JEFF);
        Arrays.asList(new StrikeFrame(),
                new SpareFrame(SEVEN_PINFALLS),
                new NormalFrame(NINE_PINFALLS, ZERO_PINFALL),
                new StrikeFrame(),
                new NormalFrame(ZERO_PINFALL, EIGHT_PINFALLS),
                new SpareFrame(EIGHT_PINFALLS),
                new NormalFrame(FAIL, SIX_PINFALLS),
                new StrikeFrame(),
                new StrikeFrame(),
                new FinalFrameWithThridRoll(TEN_PINFALLS, EIGHT_PINFALLS, ONE_PINFALLS))
                .forEach(builder::addFrame);
        return builder.build();
    }
}