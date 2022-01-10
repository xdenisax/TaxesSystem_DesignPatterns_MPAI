package visitor;

import entities.Entity;
import entities.Individual;
import entities.JuridicalEntity;

import java.util.List;

public class FileExportVisitor implements Visitor{

    public String getTextToWrite(List<Entity> entities) {
        StringBuilder sb = new StringBuilder();
        for (Entity entity: entities) {
            sb.append(entity.accept(this)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitIndividual(Individual individual) {
        StringBuilder sb = new StringBuilder();
        sb.append(individual.getName())
                .append(", ")
                .append(individual.getIdentifier())
                .append(", ")
                .append(individual.getTaxAmount());
        return sb.toString();
    }

    @Override
    public String visitJuridical(JuridicalEntity juridicalEntity) {
        StringBuilder sb = new StringBuilder();
        sb.append(juridicalEntity.getName())
                .append(", ")
                .append(juridicalEntity.getIdentifier())
                .append(", ")
                .append(juridicalEntity.getAddress())
                .append(", ")
                .append(juridicalEntity.getTaxAmount());
        return sb.toString();
    }
}
