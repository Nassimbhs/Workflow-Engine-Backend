package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CvDto {

    private Long id;
    private String prenom;
    private String nomFamille;
    private String email;
    private String titreProfil;
    private Long tel;
    private String addresse;
    private String ville;
    private List<FormationDto> formationDtos = new ArrayList<FormationDto>();
    private List<CompetenceDto> competenceDtos = new ArrayList<CompetenceDto>();

}
