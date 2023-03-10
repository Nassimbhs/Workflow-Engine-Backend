package Workflow.example.Workflow.Entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class DonneeDeclencheur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String urlWebhook;
    private String tokenWenhook;
    private String localhostDB;
    private String usernameDB;
    private String passwordDB;
    @ManyToOne
    private TypeDeclencheur TypeDeclencheurDonneeDeclencheur;

}