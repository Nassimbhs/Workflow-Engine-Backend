package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.ActiviteDto;
import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Tache;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacheConverter {

    public TacheDto entityToDto(Tache tache){
        TacheDto dto = new TacheDto();
        dto.setId(tache.getId());
        return dto;
    }
    public List<TacheDto> entityToDto(List<Tache> taches)
    {
        return taches.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
