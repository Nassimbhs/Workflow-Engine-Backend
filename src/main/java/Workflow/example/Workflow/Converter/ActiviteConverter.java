package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.ActiviteDto;
import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActiviteConverter {

    @Autowired
    private TacheConverter tacheConverter;
    @Autowired
    private LienActiviteConverter lienActiviteConverter;
    public ActiviteDto entityToDto(Activite activite){
        ActiviteDto dto = new ActiviteDto();
        dto.setId(activite.getId());
        dto.setName(activite.getName());
        dto.setCreationDate(activite.getCreationDate());
        dto.setDescription(activite.getDescription());
        dto.setStartDate(activite.getStartDate());
        dto.setEndDate(activite.getEndDate());
        dto.setTacheDtoList(tacheConverter.entityToDto(activite.getTaches()));
        dto.setLienActiviteDtos(lienActiviteConverter.entityToDto(activite.getLienActivites()));
        return dto;
    }
    public List<ActiviteDto> entityToDto(List<Activite> activites)
    {
        return activites.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
