package Workflow.example.Workflow.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LienActivite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source ;
    private String target ;

    @ManyToOne
    private Activite activiteLien;
}
