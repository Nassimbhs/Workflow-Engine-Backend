package Workflow.example.Workflow.Entity;

import Workflow.example.Workflow.Listener.TacheAtraiterListener;
import Workflow.example.Workflow.Listener.TacheListener;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@EntityListeners(TacheAtraiterListener.class)
public class TacheAtraiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private String statut;
    private String action;
    private String approbation;
    private Long responsable;
    private String emailResponsable;
    @ManyToOne
    private Tache tacheAtraite;

}
