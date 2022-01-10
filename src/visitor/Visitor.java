package visitor;

import entities.Individual;
import entities.JuridicalEntity;

public interface Visitor {
    String visitIndividual(Individual individual);
    String visitJuridical(JuridicalEntity juridicalEntity);
}
