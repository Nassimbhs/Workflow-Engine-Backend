package Workflow.example.Workflow.Entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private TacheAtraiter tacheAtraiter;

}
