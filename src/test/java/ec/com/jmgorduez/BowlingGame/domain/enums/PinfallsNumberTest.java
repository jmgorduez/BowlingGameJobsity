package ec.com.jmgorduez.BowlingGame.domain.enums;

import org.junit.jupiter.api.Test;

import static ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber.*;
import static ec.com.jmgorduez.BowlingGame.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinfallsNumberTest {

    @Test
    void getValue() {
        assertThat(FAIL.getValue())
                .isEqualTo(ZERO);
        assertThat(ONE_PINFALLS.getValue())
                .isEqualTo(ONE);
        assertThat(NINE_PINFALLS.getValue())
                .isEqualTo(NINE);
    }

    @Test
    void toStringTest() {
        assertThat(FAIL.toString())
                .isEqualTo(F);
        assertThat(ONE_PINFALLS.toString())
                .isEqualTo(ONE.toString());
        assertThat(THREE_PINFALLS.toString())
                .isEqualTo(THREE.toString());
        assertThat(TEN_PINFALLS.toString())
                .isEqualTo(X);
    }

    @Test
    void valueOf() {
        assertThat(PinfallsNumber.valueOf(THREE).get())
                .isEqualTo(THREE_PINFALLS);
        assertThat(PinfallsNumber.valueOf(NINE).get())
                .isEqualTo(NINE_PINFALLS);
        assertThat(PinfallsNumber.valueOf(TEN).get())
                .isEqualTo(TEN_PINFALLS);
    }

    @Test
    void parse() {
        assertThat(PinfallsNumber.parse(F))
                .isEqualTo(FAIL);
        assertThat(PinfallsNumber.parse(ZERO.toString()))
                .isEqualTo(ZERO_PINFALL);
        assertThat(PinfallsNumber.parse(FOUR.toString()))
                .isEqualTo(FOUR_PINFALLS);
        assertThat(PinfallsNumber.parse(TEN.toString()))
                .isEqualTo(TEN_PINFALLS);
        assertThat(PinfallsNumber.parse(X))
                .isEqualTo(TEN_PINFALLS);
        assertThatThrownBy(() -> PinfallsNumber.parse(SLASH))
                .isInstanceOf(IllegalArgumentException.class);
    }
}