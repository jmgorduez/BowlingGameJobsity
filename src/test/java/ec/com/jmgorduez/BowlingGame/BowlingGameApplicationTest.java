package ec.com.jmgorduez.BowlingGame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static ec.com.jmgorduez.BowlingGame.utils.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameApplicationTest {


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void getFilePath() throws IOException {
        assertThat(BowlingGameApplication.bufferedReader().isPresent())
                .isFalse();
        assertThat(BowlingGameApplication.bufferedReader(new String[]{INPUT_FILE_NAME_CARL_PERFECT}).get().readLine())
                .isEqualTo(CARL_10);
    }

    @Test
    void main() {
        BowlingGameApplication.main(new String[]{INPUT_FILE_NAME_CARL_PERFECT});
        assertThat(outContent.toString())
                .isEqualTo(PLAYER_SCORES_HEADER.concat(System.lineSeparator())
                        .concat(SCORE_CARL_TO_SHOW.concat(System.lineSeparator())));
    }

    @Test
    void mainWithInvalidRoll() {
        BowlingGameApplication.main(new String[]{INPUT_FILE_NAME_CARL_INVALID_ROLL});
        assertThat(errContent.toString())
                .contains(UNSUPPORTED_FORMAT_MESSAGE);
    }

    @Test
    void mainWithFileNoFound() {
        BowlingGameApplication.main(new String[]{INPUT_FILE_NO_FOUND_NAME});
        assertThat(errContent.toString())
                .contains(FILE_NO_FOUND_MESSAGE);
    }
}