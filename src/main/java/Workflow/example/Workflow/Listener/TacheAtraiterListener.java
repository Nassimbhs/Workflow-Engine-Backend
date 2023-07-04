package Workflow.example.Workflow.Listener;

import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Entity.Workflow;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class TacheAtraiterListener {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @PostPersist
    public void afterPersist(TacheAtraiter tacheAtraiter) {
        Tache tache = tacheAtraiter.getTacheAtraite();
        Workflow workflow = tache.getWorkflowTache();
        if (workflow != null && "en cours".equalsIgnoreCase(workflow.getEtat())) {
            System.out.println(tacheAtraiter.getEmailResponsable());
//            sendEmail(tacheAtraiter.getEmailResponsable(),
//                    "Nouvelle tâche à traiter",
//                    "Tache appelée : " + "'" + tacheAtraiter.getName() + "'" + " a été ajouté.");
        }
    }

    @PostUpdate
    public void afterUpdate(TacheAtraiter tacheAtraiter) {
        Tache tache = tacheAtraiter.getTacheAtraite();
        Workflow workflow = tache.getWorkflowTache();
        if (workflow != null && "en cours".equalsIgnoreCase(workflow.getEtat())) {
            System.out.println("nom de la tache modifier : " + tacheAtraiter.getName());
        }
    }

    @PostRemove
    public void afterRemove(TacheAtraiter tacheAtraiter) {
        Tache tache = tacheAtraiter.getTacheAtraite();
        Workflow workflow = tache.getWorkflowTache();
        if (workflow != null && "en cours".equalsIgnoreCase(workflow.getEtat())) {
            System.out.println("nom de la tache supprimer : " + tacheAtraiter.getName());
        }
    }

}
