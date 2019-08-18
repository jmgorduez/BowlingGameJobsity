package ec.com.jmgorduez.BowlingGame.domain.abstractions;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;

import java.util.List;

public interface IPlayerScore {
    String getPlayerName();
    List<IBowlingFrame> frames();
    Integer accumulatedScoreAt(IBowlingFrame frame);
}
