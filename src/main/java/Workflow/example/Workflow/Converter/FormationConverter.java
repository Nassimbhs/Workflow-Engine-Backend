package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.FormationDto;
import Workflow.example.Workflow.Entity.Formation;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormationConverter {
    public FormationDto entityToDto(Formation formation){
        FormationDto dto = new FormationDto();
        dto.setId(formation.getId());
        dto.setNomFormation(formation.getNomFormation());
        dto.setEtablissement(formation.getEtablissement());
        dto.setDateDeb(formation.getDateDeb());
        dto.setDateFin(formation.getDateFin());
        return dto;
    }
    public List<FormationDto> entityToDto(List<Formation> formations)
    {
        return formations.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
