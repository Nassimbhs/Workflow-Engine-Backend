package Workflow.example.Workflow.Entity;

import Workflow.example.Workflow.Listener.TacheListener;
import Workflow.example.Workflow.Listener.WorkflowListener;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@EntityListeners(WorkflowListener.class)
public class Workflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;
    private String etat;
    private String declencheur;
    private String webhookUrl;
    private String jdbcUrl;
    private String username;
    private String password;
    private String sgbd;
    private String tacheAecouter;
    private String evenement;

    @OneToMany(mappedBy = "workflowTache", cascade = CascadeType.ALL)
    private List<Tache> taches = new ArrayList<>();

}