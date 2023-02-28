package Workflow.example.Workflow.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TypeDeclencheur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    private Workflow workflowTypeDeclencheur;

    @OneToMany(mappedBy = "TypeDeclencheurDonneeDeclencheur", cascade = CascadeType.ALL)
    private List<DonneeDeclencheur> donneeDeclencheurs = new ArrayList<>();

}
