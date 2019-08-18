package ec.com.jmgorduez.BowlingGame.domain.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;

public enum PinfallsNumber {
    FAIL(ZERO, F),
    ZERO_PINFALL(ZERO),
    ONE_PINFALLS(ONE),
    TWO_PINFALLS(TWO),
    THREE_PINFALLS(THREE),
    FOUR_PINFALLS(FOUR),
    FIVE_PINFALLS(FIVE),
    SIX_PINFALLS(SIX),
    SEVEN_PINFALLS(SEVEN),
    EIGHT_PINFALLS(EIGHT),
    NINE_PINFALLS(NINE),
    TEN_PINFALLS(TEN, X);

    private final Integer value;
    private final String stringValue;

    PinfallsNumber(Integer value, String... stringValue) {
        this.value = value;
        this.stringValue = stringValue.length == ZERO ? value.toString() : stringValue[ZERO];
    }

    public Integer getValue() {
        return value;
    }

    public String toString() {
        switch (this) {
            case FAIL:
                return F;
            case TEN_PINFALLS:
                return X;
            default:
                return this.getValue().toString();
        }
    }

    public static Optional<PinfallsNumber> valueOf(Integer value) {
        return Arrays.stream(values())
                .filter(PinfallsNumber::isNotFail)
                .filter(pinfallsNumber -> pinfallsNumber.value.equals(value))
                .reduce(PinfallsNumber::getFirstPinfallsNumber);
    }

    public static PinfallsNumber parse(String value)
            throws IllegalArgumentException {

        if (TEN_PINFALLS.value.toString().equals(value)) {
            return TEN_PINFALLS;
        }
        return Arrays.stream(values())
                .filter(anyMatchStringValueWith(value))
                .reduce(PinfallsNumber::getFirstPinfallsNumber).orElseThrow(IllegalArgumentException::new);
    }

    private static Predicate<PinfallsNumber> anyMatchStringValueWith(String value) {
        return pinfallsNumber -> pinfallsNumber.stringValue.equals(value);
    }

    private static PinfallsNumber getFirstPinfallsNumber(PinfallsNumber... pinfallsNumber) {
        return pinfallsNumber[ZERO];
    }

    private static boolean isNotFail(PinfallsNumber pinfallsNumber) {
        return !pinfallsNumber.equals(FAIL);
    }
}