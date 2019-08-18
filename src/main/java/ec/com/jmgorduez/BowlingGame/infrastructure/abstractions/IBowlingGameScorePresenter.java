package ec.com.jmgorduez.BowlingGame.infrastructure.abstractions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface IBowlingGameScorePresenter {
    void presentScore(Supplier<String> readLine, Consumer<String> writeOutput)
            throws IllegalArgumentException;
}
