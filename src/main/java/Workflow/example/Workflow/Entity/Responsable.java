package Workflow.example.Workflow.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Responsable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToOne
    private Workflow workflowResponsable;
}
