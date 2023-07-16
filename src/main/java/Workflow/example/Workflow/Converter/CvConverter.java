package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.CvDto;
import Workflow.example.Workflow.Entity.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CvConverter {

    @Autowired
    private FormationConverter formationConverter;
    @Autowired
    private CompetenceConverter competenceConverter;

    public CvDto entityToDto(Cv cv){
        CvDto dto = new CvDto();
        dto.setId(cv.getId());
        dto.setEmail(cv.getEmail());
        dto.setTel(cv.getTel());
        dto.setAddresse(cv.getAddresse());
        dto.setPrenom(cv.getPrenom());
        dto.setNomFamille(cv.getNomFamille());
        dto.setVille(cv.getVille());
        dto.setTitreProfil(cv.getTitreProfil());
        dto.setFormationDtos(formationConverter.entityToDto(cv.getFormations()));
        dto.setCompetenceDtos(competenceConverter.entityToDto(cv.getCompetences()));
        return dto;
    }
    public List<CvDto> entityToDto(List<Cv> cvs)
    {
        return cvs.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
