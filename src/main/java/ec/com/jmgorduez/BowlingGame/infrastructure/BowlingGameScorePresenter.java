package ec.com.jmgorduez.BowlingGame.infrastructure;

import ec.com.jmgorduez.BowlingGame.infrastructure.abstractions.IBowlingGameScorePresenter;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IBowlingGameScoreReader;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IFrameParser;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IPlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static java.util.Optional.ofNullable;

public class BowlingGameScorePresenter implements IBowlingGameScorePresenter {

    private IFrameParser frameParser;
    private IBowlingGameScoreReader bowlingGameScoreReader;

    public BowlingGameScorePresenter(IFrameParser frameParser, IBowlingGameScoreReader bowlingGameScoreReader) {
        this.frameParser = frameParser;
        this.bowlingGameScoreReader = bowlingGameScoreReader;
    }

    @Override
    public void presentScore(Supplier<String> readLine, Consumer<String> writeOutput)
            throws IllegalArgumentException {

        List<String> rolls = readAllOfLines(readLine);
        writeOutput.accept(PLAYER_SCORES_HEADER);
        bowlingGameScoreReader.readScores(rolls, frameParser::parse)
                .forEach(printPlayerScore(writeOutput));
    }

    private List<String> readAllOfLines(Supplier<String> readLine) {
        List<String> linesRead = new ArrayList<>();
        try {
            while (true) {
                linesRead.add(ofNullable(readLine.get()).orElseThrow(UnsupportedOperationException::new));
            }
        } catch (UnsupportedOperationException error) {
            return linesRead;
        }
    }

    private Consumer<IPlayerScore> printPlayerScore(Consumer<String> showScore) {
        return playerScore ->
                showScore.accept(
                        playerScore.getPlayerName().concat(System.lineSeparator())
                                .concat(PINFALLS_TEXT).concat(TAB)
                                .concat(playerScore.frames().stream()
                                        .map(IBowlingFrame::toString)
                                        .collect(Collectors.joining(TAB))).concat(System.lineSeparator())
                                .concat(SCORE_TEXT).concat(TAB).concat(TAB)
                                .concat(playerScore.frames().stream()
                                        .map(playerScore::accumulatedScoreAt)
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(TAB.concat(TAB)))));
    }
}
