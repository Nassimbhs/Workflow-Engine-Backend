package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.InteretDto;
import Workflow.example.Workflow.Entity.Interet;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InteretConverter {

    public InteretDto entityToDto(Interet interet){
        InteretDto dto = new InteretDto();
        dto.setId(interet.getId());
        dto.setInteret(interet.getInteret());
        return dto;
    }

    public List<InteretDto> entityToDto(List<Interet> interets)
    {
        return interets.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
