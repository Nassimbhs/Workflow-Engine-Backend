package Workflow.example.Workflow.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Responsable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    private Workflow workflowResponsable;
}
