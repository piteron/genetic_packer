package genetic.packer.generation.dto;

import genetic.selectors.dto.RatedIndividual;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import java.util.Collection;

/**
 * @author piotr.larysz
 */
@GeneratePojoBuilder
public class Generation<V extends Comparable<V>, T> {

    private Integer id;

    private Collection<RatedIndividual<V, T>> ratedIndividuals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<RatedIndividual<V, T>> getRatedIndividuals() {
        return ratedIndividuals;
    }

    public void setRatedIndividuals(Collection<RatedIndividual<V, T>> ratedIndividuals) {
        this.ratedIndividuals = ratedIndividuals;
    }
}
