package Workflow.example.Workflow.Entity;

import lombok.*;
import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name = "tache_user",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();
}
