package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.LangueDto;
import Workflow.example.Workflow.Entity.Langue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LangueConverter {

    public LangueDto entityToDto(Langue langue){
        LangueDto dto = new LangueDto();
        dto.setId(langue.getId());
        dto.setNom(langue.getNom());
        dto.setNiveau(langue.getNiveau());
        return dto;
    }
    public List<LangueDto> entityToDto(List<Langue> langues)
    {
        return langues.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
