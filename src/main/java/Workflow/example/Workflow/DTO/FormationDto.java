package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class FormationDto {
    private Long id;
    private String nomFormation;
    private String etablissement;
    private Date dateDeb;
    private Date dateFin;
    private String description;
}
