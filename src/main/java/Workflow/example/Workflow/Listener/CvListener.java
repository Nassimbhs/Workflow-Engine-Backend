package Workflow.example.Workflow.Listener;

import Workflow.example.Workflow.Entity.Cv;
import jakarta.persistence.PostPersist;
import org.springframework.stereotype.Component;

@Component
public class CvListener {

    @PostPersist
    public void afterPersist(Cv cv) {
        System.out.println("CV " + cv.getId() + " ajouté ");
    }

}