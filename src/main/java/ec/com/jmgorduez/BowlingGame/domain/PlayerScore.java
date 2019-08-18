package ec.com.jmgorduez.BowlingGame.domain;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.IPlayerScore;
import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.ONE;
import static java.util.Optional.ofNullable;

public final class PlayerScore implements IPlayerScore {

    protected List<Optional<IBowlingFrame>> frames;
    @Getter
    protected String playerName;

    public PlayerScore(PlayerScoreBuilder builder) {
        this.playerName = builder.playerName;
        this.frames = builder.frames;
    }

    @Override
    public List<IBowlingFrame> frames() {
        return this.frames.stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Integer accumulatedScoreAt(IBowlingFrame frame) {
        return frames().stream()
                .filter(isBeforeInTheList(frame))
                .mapToInt(IBowlingFrame::pointsForScore)
                .sum();
    }

    private Predicate<IBowlingFrame> isBeforeInTheList(IBowlingFrame limitFrame) {
        return frame -> frames().indexOf(frame) <= frames().indexOf(limitFrame);
    }

    Optional<IBowlingFrame> nextFrameOf(IBowlingFrame frame) {
        Integer indexOfNextFrame = frames.indexOf(ofNullable(frame)) + ONE;
        return this.frames.get(indexOfNextFrame);
    }

    public static class PlayerScoreBuilder{
        @Getter
        private List<Optional<IBowlingFrame>> frames;
        @Getter @NonNull
        private String playerName;

        public PlayerScoreBuilder(String playerName){
            this.playerName = playerName;
            this.frames = new ArrayList<>();
        }

        public PlayerScoreBuilder addFrame(IBowlingFrame frame) {
            this.frames.add(ofNullable(frame));
            frame.setNextFrameFunction(this::nextFrameOf);
            return this;
        }

        public PlayerScore build(){
            return new PlayerScore(this);
        }

        private Optional<IBowlingFrame> nextFrameOf(IBowlingFrame frame) {
            Integer indexOfNextFrame = this.frames.indexOf(ofNullable(frame)) + ONE;
            return this.frames.get(indexOfNextFrame);
        }
    }
}
