package genetic.statistics.average

import genetic.api.generation.Generation

class AverageFitnessResolver : (Generation<*>) -> Double {

  override fun invoke(generation: Generation<*>) = generation.ratedIndividuals
      .asSequence()
      .map { it.fitness.score }
      .average()
}