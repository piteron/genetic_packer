package genetic.recombinators.impl.child

import genetic.recombinators.chromosome.ChromosomeRecombinator
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class TwoChildrenRecombinator<T, U>(
    @Qualifier("twoChromosomesRecombinator") chromosomeRecombinator: ChromosomeRecombinator<T, U>
) : ChildRecombinator<T, U>(chromosomeRecombinator)