package Workflow.example.Workflow.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDeb;
    private Date dateFin;
    private String type;
    private String statut;
    private String commentaire;
    private String responsable;

    @ManyToOne
    private Tache tacheConge;

}
