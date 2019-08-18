package ec.com.jmgorduez.BowlingGame.domain.abstractions;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;

public interface IFrameParser {

    IBowlingFrame parse(String frameValues)
            throws IllegalArgumentException;
}
