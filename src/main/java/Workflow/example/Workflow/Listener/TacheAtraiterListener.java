package Workflow.example.Workflow.Listener;

import Workflow.example.Workflow.Entity.TacheAtraiter;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;

@Component
public class TacheAtraiterListener {
    @PostPersist
    public void afterPersist(TacheAtraiter tacheAtraiter) {
        System.out.println("nom de la tache ajouter : "+tacheAtraiter.getName());
    }

    @PostUpdate
    public void afterUpdate(TacheAtraiter tacheAtraiter) {
        System.out.println("nom de la tache modifier : "+tacheAtraiter.getName());
    }

    @PostRemove
    public void afterRemove(TacheAtraiter tacheAtraiter) {
        System.out.println("nom de la tache supprimer : "+tacheAtraiter.getName());
    }

}
