package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.Entity.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacheConverter {
    @Autowired
    private LienTacheConverter lienTacheConverter;
    public TacheDto entityToDto(Tache tache){
        TacheDto dto = new TacheDto();
        dto.setId(tache.getId());
        dto.setName(tache.getName());
        dto.setCreationDate(tache.getCreationDate());
        dto.setDescription(tache.getDescription());
        dto.setStartDate(tache.getStartDate());
        dto.setEndDate(tache.getEndDate());
        dto.setLienTacheDtos(lienTacheConverter.entityToDto(tache.getLienTaches()));
        return dto;
    }
    public List<TacheDto> entityToDto(List<Tache> taches)
    {
        return taches.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
