package Workflow.example.Workflow.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class LienActivite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", initialValue = 100, allocationSize = 1)
    private Long id;
    private String source ;
    private String target ;

    @ManyToOne
    private Activite activiteLien;
}