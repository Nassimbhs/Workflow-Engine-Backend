package Workflow.example.Workflow.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomFormation;
    private String etablissement;
    private Date dateDeb;
    private Date dateFin;
    private String description;
    @ManyToOne
    private Cv cv;
}
