package Workflow.example.Workflow.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
public class Historique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat;
    @OneToOne
    private Tache tache;

}
