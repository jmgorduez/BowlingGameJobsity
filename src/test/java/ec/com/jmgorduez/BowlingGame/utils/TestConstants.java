package ec.com.jmgorduez.BowlingGame.utils;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.IBowlingFrame;

import java.io.File;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public class TestConstants {

    public static final Integer TWENTY = 20;
    public static final Integer THIRTY = 30;

    public static final String JOHN = "John";
    public static final String JEFF = "Jeff";
    public static final String CARL = "Carl";

    public static final String JEFF_F = "Jeff\tF";
    public static final String JEFF_0 = "Jeff\t0";
    public static final String JEFF_1 = "Jeff\t1";
    public static final String JEFF_2 = "Jeff\t2";
    public static final String JEFF_3 = "Jeff\t3";
    public static final String JEFF_6 = "Jeff\t6";
    public static final String JEFF_7 = "Jeff\t7";
    public static final String JEFF_8 = "Jeff\t8";
    public static final String JEFF_9 = "Jeff\t9";
    public static final String JEFF_10 = "Jeff\t10";

    public static final String JOHN_0 = "John\t0";
    public static final String JOHN_1 = "John\t1";
    public static final String JOHN_3 = "John\t3";
    public static final String JOHN_4 = "John\t4";
    public static final String JOHN_6 = "John\t6";
    public static final String JOHN_7 = "John\t7";
    public static final String JOHN_8 = "John\t8";
    public static final String JOHN_9 = "John\t9";
    public static final String JOHN_10 = "John\t10";
    public static final String GET_NEXT_FRAME_FUNCTION = "getNextFrameFunction";

    public static final String STRIKE_FRAME_STRING = "10";
    public static final String SPARE_FRAME_STRING_8_2 = "8 2";
    public static final String NORMAL_FRAME_STRING_7_0 = "7 0";
    public static final String FINAL_FRAME_STRING_8_1_0 = "8 1 0";

    public static final String STRIKE_FRAME = X;
    public static final String SPARE_FRAME_7_3 = SEVEN.toString().concat(BLANK_SPACE).concat(THREE.toString());
    public static final String NORMAL_FRAME_9_0 = NINE.toString().concat(BLANK_SPACE).concat(ZERO.toString());
    public static final String NORMAL_FRAME_0_8 = ZERO.toString().concat(BLANK_SPACE).concat(EIGHT.toString());
    public static final String SPARE_FRAME_8_2 = EIGHT.toString().concat(BLANK_SPACE).concat(TWO.toString());
    public static final String NORMAL_FRAME_F_6 = F.concat(BLANK_SPACE).concat(SIX.toString());
    public static final String FINAL_FRAME_10_8_1 = X.concat(BLANK_SPACE).concat(EIGHT.toString()).concat(BLANK_SPACE).concat(ONE.toString());


    public static final String INPUT_FILE_PATH = "src.test.resources.inputFile."
            .replace(".", File.separator);
    public static final String INPUT_FILE_NAME_CARL_PERFECT = INPUT_FILE_PATH.concat("inputCarlPerfectScore.txt");
    public static final String INPUT_FILE_NAME_CARL_INVALID_ROLL = INPUT_FILE_PATH.concat("inputCarlInvalidRoll.txt");
    public static final String INPUT_FILE_NO_FOUND_NAME = INPUT_FILE_PATH.concat("FileNoFound.txt");

    public static final String CARL_10 = CARL.concat(TAB).concat(TEN.toString());

    public static final String SCORE_CARL_TO_SHOW = CARL.concat(System.lineSeparator())
            .concat(PINFALLS_TEXT).concat("..X..X..X..X..X..X..X..X..X.X.X.X")
            .concat(System.lineSeparator())
            .concat(SCORE_TEXT).concat("..30..60..90..120..150..180..210..240..270..300")
            .replace(".","\t");
    public static final String SCORE_JEFF_TO_SHOW = JEFF.concat(System.lineSeparator())
            .concat(PINFALLS_TEXT).concat("..X.7./.9.0..X.0.8.8./.F.6..X..X.X.8.1")
            .concat(System.lineSeparator())
            .concat(SCORE_TEXT).concat("..20..39..48..66..74..84..90..120..148..167")
            .replace(".","\t");
    public static final String SCORE_JOHN_TO_SHOW = JOHN.concat(System.lineSeparator())
            .concat(PINFALLS_TEXT).concat(".3./.6.3..X.8.1..X..X.9.0.7./.4.4.X.9.0"
            .concat(System.lineSeparator()))
            .concat(SCORE_TEXT).concat("..16..25..44..53..82..101..110..124..132..151")
            .replace(".","\t");

    public static final String JEFF_INVALID_ROLL = JEFF.concat(TAB).concat(THIRTY.toString()).concat(ZERO.toString());

}
