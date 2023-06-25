package Workflow.example.Workflow.Listener;

import Workflow.example.Workflow.Entity.Workflow;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;

@Component
public class WorkflowListener {

    @PostPersist
    public void afterPersist(Workflow workflow) {
        if (workflow.getEvenement() != null && workflow.getEvenement().equals("Ajout")) {
            System.out.println("Écoutez l'événement d'ajout de la table " + workflow.getTacheAecouter());
        }
    }

    @PostUpdate
    public void afterUpdate(Workflow workflow) {
        if (workflow.getEvenement() != null && workflow.getEvenement().equals("Modification")) {
            System.out.println("Écoutez l'événement de modification de la table "+workflow.getTacheAecouter());
        }
    }

    @PostRemove
    public void afterRemove(Workflow workflow) {
        if (workflow.getEvenement() != null && workflow.getEvenement().equals("Suppression")) {
            System.out.println("Écoutez l'événement de suppression de la table " + workflow.getTacheAecouter());
        }
    }

}
