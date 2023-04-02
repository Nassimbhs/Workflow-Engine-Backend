package Workflow.example.Workflow.Entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Tache  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String etat;
    private String priorite;

    @ManyToOne
    private Workflow workflowTache;

}