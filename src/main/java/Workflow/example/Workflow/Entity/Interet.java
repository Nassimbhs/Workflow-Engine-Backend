package Workflow.example.Workflow.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Interet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String interet;

    @ManyToOne
    private Cv cv;

}
