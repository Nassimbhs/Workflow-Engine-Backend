package Workflow.example.Workflow.Entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Langue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String niveau;

    @ManyToOne
    private Cv cv;
}
