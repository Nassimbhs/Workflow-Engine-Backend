package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.DTO.UserDto;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacheConverter {
    @Autowired
    private LienTacheConverter lienTacheConverter;

    @Autowired
    private UserConverter userConverter;

    public TacheDto entityToDto(Tache tache){
        TacheDto dto = new TacheDto();
        dto.setId(tache.getId());
        dto.setName(tache.getName());
        dto.setCreationDate(tache.getCreationDate());
        dto.setDescription(tache.getDescription());
        dto.setStartDate(tache.getStartDate());
        dto.setEndDate(tache.getEndDate());
        dto.setStatut(tache.getStatut());
        dto.setTriggerType(tache.getTriggerType());
        dto.setLienTacheDtos(lienTacheConverter.entityToDto(tache.getLienTaches()));
        dto.setUserDtoList(userConverter.entityToDto(tache.getUserList()));
        return dto;
    }
    public List<TacheDto> entityToDto(List<Tache> taches)
    {
        return taches.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
