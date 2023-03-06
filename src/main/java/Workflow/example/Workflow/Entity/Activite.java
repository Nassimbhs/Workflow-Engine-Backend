package Workflow.example.Workflow.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private String target;

    @ManyToOne
    private Workflow workflowActivite;

    @OneToMany(mappedBy = "workflowTache", cascade = CascadeType.ALL)
    private List<Tache> taches = new ArrayList<>();

    @OneToMany(mappedBy = "activiteLien", cascade = CascadeType.ALL)
    private List<LienActivite> lienActivites = new ArrayList<>();

}
