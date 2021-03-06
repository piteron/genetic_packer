package genetic.packer.fx.calculation.impl;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

@Component
public class MaxBoundsCalculator implements BiFunction<Double, Double, Integer> {

//    private static final int BOUNDS_ADJUSTMENT = 1;

    @Override
    public Integer apply(Double boundsMax, Double boxSize) {
        return (int) (boundsMax - boxSize)/* + BOUNDS_ADJUSTMENT*/;
    }
}
