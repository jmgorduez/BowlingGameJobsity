package ec.com.jmgorduez.BowlingGame.domain.readers;

import ec.com.jmgorduez.BowlingGame.domain.PlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IBowlingGameScoreReader;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.IPlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.TEN_PINFALLS;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public class BowlingGameScoreReader implements IBowlingGameScoreReader {

    @Override
    public List<IPlayerScore> readScores(List<String> linesRead, Function<String, IBowlingFrame> readFrame)
            throws IllegalArgumentException {
        List<PlayerScore.PlayerScoreBuilder> playerScores = getPlayerScores(linesRead);
        playerScores.forEach(getFrames(linesRead, readFrame));
        return playerScores.stream()
                .map(PlayerScore.PlayerScoreBuilder::build)
                .collect(Collectors.toList());
    }

    private Consumer<PlayerScore.PlayerScoreBuilder> getFrames(List<String> rolls, Function<String, IBowlingFrame> readFrame) {

        return builder -> getRollsByPlayerFrames(builder.getPlayerName(), rolls)
                .map(readFrame)
                .forEach(builder::addFrame);
    }

    private List<PlayerScore.PlayerScoreBuilder> getPlayerScores(List<String> rolls) {
        return getDifferentPlayersName(rolls)
                .map(PlayerScore.PlayerScoreBuilder::new)
                .collect(Collectors.toList());
    }

    private Stream<String> getDifferentPlayersName(List<String> rolls) {
        return rolls.stream()
                .map(this::getPlayerName)
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream();
    }

    Stream<String> getRollsByPlayerFrames(String playerName, List<String> rolls) {
        Queue<PinfallsNumber> pinfallsValues = getPinfallsValues(playerName, rolls);
        List<String> rollsByFrame = new ArrayList<>();
        while (hasPinfallsValuesToProcess(pinfallsValues)) {
            if (isAFinalFrame(rollsByFrame)) {
                rollsByFrame.add(joinRollsOfAFrame(pinfallsValues::poll, pinfallsValues::poll, pinfallsValues::poll));
                break;
            }
            if (isAStrikeFrame(pinfallsValues::peek)) {
                rollsByFrame.add(joinRollsOfAFrame(pinfallsValues::poll));
                continue;
            }
            rollsByFrame.add(joinRollsOfAFrame(pinfallsValues::poll, pinfallsValues::poll));
        }
        return rollsByFrame.stream();
    }

    private Stream<String> getPlayerRolls(String playerName, List<String> rolls) {
        return rolls.stream()
                .filter(roll -> roll.contains(playerName));
    }

    private boolean hasPinfallsValuesToProcess(Queue<PinfallsNumber> pinfallsValues) {
        return !pinfallsValues.isEmpty();
    }

    private ArrayDeque<PinfallsNumber> getPinfallsValues(String playerName, List<String> rolls) {
        return getPlayerRolls(playerName, rolls)
                .map(this::getPinfallsValue)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private boolean isAStrikeFrame(Supplier<PinfallsNumber> roll) {
        return roll.get().equals(TEN_PINFALLS);
    }

    private boolean isAFinalFrame(List<String> rollsByFrame) {
        return rollsByFrame.size() >= NINE;
    }

    @SafeVarargs
    private final String joinRollsOfAFrame(Supplier<PinfallsNumber>... elements) {
        return Arrays.stream(elements)
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .map(PinfallsNumber::toString)
                .collect(Collectors.joining(BLANK_SPACE));
    }

    private PinfallsNumber getPinfallsValue(String roll) {
        return PinfallsNumber.parse(roll.split(TAB)[ONE]);
    }

    private String getPlayerName(String roll) {
        return roll.split(TAB)[ZERO];
    }
}
