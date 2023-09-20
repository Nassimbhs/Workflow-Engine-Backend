package Workflow.example.Workflow.Entity;

import Workflow.example.Workflow.Listener.TacheAtraiterListener;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Long workflowId;
    @ManyToOne
    private Tache tacheAtraite;

    @OneToMany(mappedBy = "tacheAtraiter", cascade = CascadeType.ALL)
    List<Conge> conges = new ArrayList<>();

    @ManyToMany(mappedBy = "tachesAtraiter")
    private List<Cv> cvs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tache_atraiter_jsondata",
            joinColumns = @JoinColumn(name = "tache_atraiter_id"),
            inverseJoinColumns = @JoinColumn(name = "jsondata_id")
    )
    private List<JsonData> jsonDatas = new ArrayList<>();

}
