package ec.com.jmgorduez.BowlingGame;


import ec.com.jmgorduez.BowlingGame.infrastructure.BowlingGameScorePresenter;
import ec.com.jmgorduez.BowlingGame.domain.readers.BowlingGameScoreReader;
import ec.com.jmgorduez.BowlingGame.domain.parsers.FrameParser;
import ec.com.jmgorduez.BowlingGame.infrastructure.abstractions.IBowlingGameScorePresenter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

import static ec.com.jmgorduez.BowlingGame.infrastructure.abstractions.ThrowingSupplier.unchecked;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class BowlingGameApplication {

    private static IBowlingGameScorePresenter scorePresenter = new BowlingGameScorePresenter(new FrameParser(),
            new BowlingGameScoreReader());

    public static void main(String[] args) {
        try {
            bufferedReader(args).ifPresent(BowlingGameApplication::proccesFile);
        }catch (IllegalArgumentException e){
            System.err.println(UNSUPPORTED_FORMAT_MESSAGE);
        }catch (FileNotFoundException e) {
            System.err.println(FILE_NO_FOUND_MESSAGE);
        }
    }

    private static void proccesFile(BufferedReader bufferedReader) {
        scorePresenter.presentScore(unchecked(bufferedReader::readLine), System.out::println);
    }

    static Optional<BufferedReader> bufferedReader(String... args)
            throws FileNotFoundException {

        if (Arrays.asList(args).isEmpty()) {
            return empty();
        }
        return of(new BufferedReader(new FileReader(args[ZERO])));
    }
}
