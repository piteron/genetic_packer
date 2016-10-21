package genetic.packer.misc;

import genetic.selectors.dto.FitnessTested;

/**
 * @author piotr.larysz
 */
public class Sorting {

    public static <M1 extends Comparable<M1>, M2 extends FitnessTested<M1>> int compareDescending(M2 first, M2 second) {
        return second.getFitness().compareTo(first.getFitness());
    }
}
