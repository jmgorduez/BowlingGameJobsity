package ec.com.jmgorduez.BowlingGame.infrastructure;

import ec.com.jmgorduez.BowlingGame.domain.parsers.FrameParser;
import ec.com.jmgorduez.BowlingGame.domain.readers.BowlingGameScoreReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.PLAYER_SCORES_HEADER;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlingGameScorePresenterTest {

    private BowlingGameScorePresenter bowlingGameScorePresenterUnderTest;
    private Queue<String> outputLines;

    @BeforeEach
    void setUp() {
        bowlingGameScorePresenterUnderTest = new BowlingGameScorePresenter(new FrameParser(), new BowlingGameScoreReader());
        outputLines = new ArrayDeque<>();
    }

    @Test
    void presentScore() {
        Queue<String> linesToRead = generateJeffAndJohnScores();
        bowlingGameScorePresenterUnderTest.presentScore(linesToRead::poll, this::writeOutput);
        assertThat(outputLines.poll())
                .isEqualTo(PLAYER_SCORES_HEADER);
        assertThat(outputLines.poll())
                .isEqualTo(SCORE_JEFF_TO_SHOW);
        assertThat(outputLines.poll())
                .isEqualTo(SCORE_JOHN_TO_SHOW);
    }

    @Test
    void presentScoreInvalidRoll() {
        Queue<String> lines = new ArrayDeque<>(Arrays.asList(JEFF_INVALID_ROLL));
        assertThatThrownBy(() ->
                bowlingGameScorePresenterUnderTest.presentScore(lines::poll, s -> {}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private void writeOutput(String output) {
        outputLines.add(output);
    }

    private ArrayDeque<String> generateJeffAndJohnScores() {
        return Arrays.asList(JEFF_10, JOHN_3, JOHN_7, JEFF_7, JEFF_3, JOHN_6, JOHN_3, JEFF_9, JEFF_0,
                JOHN_10, JEFF_10, JOHN_8, JOHN_1, JEFF_0, JEFF_8, JOHN_10, JEFF_8, JEFF_2, JOHN_10, JEFF_F, JEFF_6,
                JOHN_9, JOHN_0, JEFF_10, JOHN_7, JOHN_3, JEFF_10, JOHN_4, JOHN_4, JEFF_10, JEFF_8, JEFF_1,
                JOHN_10, JOHN_9, JOHN_0).stream()
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}