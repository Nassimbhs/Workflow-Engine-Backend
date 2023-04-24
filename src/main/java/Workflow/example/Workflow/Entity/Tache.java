package Workflow.example.Workflow.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
public class Tache implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private Workflow workflowTache;

    @OneToMany(mappedBy = "tacheLien", cascade = CascadeType.ALL)
    private List<LienTache> lienTaches = new ArrayList<>();

    @ManyToMany(mappedBy = "taches")
    private List<User> userList = new ArrayList<>();
}
