package Workflow.example.Workflow.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
public class Workflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;
    private String etat;
    private String declencheur;

    @OneToMany(mappedBy = "workflowTypeDeclencheur", cascade = CascadeType.ALL)
    private List<TypeDeclencheur> typeDeclencheurs = new ArrayList<>();

    @OneToMany(mappedBy = "workflowActivite", cascade = CascadeType.ALL)
    private List<Activite> activites = new ArrayList<>();

    @OneToMany(mappedBy = "workflowResponsable", cascade = CascadeType.ALL)
    private List<Responsable> responsables = new ArrayList<>();

}