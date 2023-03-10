package Workflow.example.Workflow.Entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Activite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;

    @ManyToOne
    private Workflow workflowActivite;

    @OneToMany(mappedBy = "workflowTache", cascade = CascadeType.ALL)
    private List<Tache> taches = new ArrayList<>();

    @OneToMany(mappedBy = "activiteLien", cascade = CascadeType.ALL)
    private List<LienActivite> lienActivites = new ArrayList<>();

}
