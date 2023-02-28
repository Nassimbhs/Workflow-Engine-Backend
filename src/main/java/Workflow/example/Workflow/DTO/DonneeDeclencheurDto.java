package Workflow.example.Workflow.DTO;

import lombok.Data;

@Data
public class DonneeDeclencheurDto {

    private Long id;
    private String urlWebhook;
    private String tokenWenhook;
    private String localhostDB;
    private String usernameDB;
    private String passwordDB;

}
