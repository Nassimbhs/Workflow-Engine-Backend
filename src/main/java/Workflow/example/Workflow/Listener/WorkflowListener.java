package Workflow.example.Workflow.Listener;

import Workflow.example.Workflow.Entity.Workflow;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import org.springframework.stereotype.Component;

@Component
public class WorkflowListener {

    @PostPersist
    public void afterPersist(Workflow workflow) {
            System.out.println("Écoutez l'événement d'ajout de la table " + workflow.getTacheAecouter());
    }

    @PostUpdate
    public void afterUpdate(Workflow workflow) {
            System.out.println("Écoutez l'événement de modification de la table "+workflow.getTacheAecouter());
    }

    @PostRemove
    public void afterRemove(Workflow workflow) {
            System.out.println("Écoutez l'événement de suppression de la table " + workflow.getTacheAecouter());
    }

}
