package Workflow.example.Workflow.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class DonneeDeclencheur {

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
