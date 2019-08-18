package ec.com.jmgorduez.BowlingGame.domain.frames;

import ec.com.jmgorduez.BowlingGame.domain.abstractions.frames.AbstractFrameWithSecondRoll;
import ec.com.jmgorduez.BowlingGame.domain.enums.PinfallsNumber;

import static ec.com.jmgorduez.BowlingGame.utils.Constants.TAB;

public class FinalFrameWithThridRoll
        extends AbstractFrameWithSecondRoll{

    private final PinfallsNumber pinfallsThridRoll;

    public FinalFrameWithThridRoll(PinfallsNumber pinfallsFirstRoll,
                                   PinfallsNumber pinfallsSecondRoll,
                                   PinfallsNumber pinfallsThridRoll) {
        super(pinfallsFirstRoll, pinfallsSecondRoll);
        this.pinfallsThridRoll = pinfallsThridRoll;
    }

    @Override
    public Integer pointsForScore() {
        return pinfallsFirstRoll.getValue() + pinfallsSecondRoll.getValue() + pinfallsThridRoll.getValue();
    }

    @Override
    public String toString(){
        return pinfallsFirstRoll.toString().concat(TAB)
                .concat(pinfallsSecondRoll.toString()).concat(TAB)
                .concat(pinfallsThridRoll.toString());
    }
}
