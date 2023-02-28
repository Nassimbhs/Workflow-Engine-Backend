package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.ActiviteDto;
import Workflow.example.Workflow.DTO.ResponsableDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Responsable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponsableConverter {

    public ResponsableDto entityToDto(Responsable responsable){
        ResponsableDto dto = new ResponsableDto();
        dto.setId(responsable.getId());
        dto.setUsername(responsable.getUsername());
        dto.setPassword(responsable.getPassword());
        return dto;
    }
    public List<ResponsableDto> entityToDto(List<Responsable> responsables)
    {
        return responsables.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
