package ec.com.jmgorduez.BowlingGame.domain.abstractions;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.IPlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;

import java.util.List;
import java.util.function.Function;

public interface IBowlingGameScoreReader {
    List<IPlayerScore> readScores(List<String> linesRead,
                                  Function<String, IBowlingFrame> readFrame);
}
