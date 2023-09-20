package Workflow.example.Workflow.Entity;

import Workflow.example.Workflow.Listener.CvListener;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EntityListeners(CvListener.class)
public class Cv implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nomFamille;
    private String email;
    private String titreProfil;
    private Long tel;
    private String addresse;
    private String ville;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Competence> competences = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Formation> formations = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Langue> langues = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
    private List<Interet> interets = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cv_tache",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "tache_id")
    )
    private List<TacheAtraiter> tachesAtraiter = new ArrayList<>();

}