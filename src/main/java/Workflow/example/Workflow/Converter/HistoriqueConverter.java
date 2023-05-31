package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.HistoriqueDto;
import Workflow.example.Workflow.Entity.Historique;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HistoriqueConverter {
    public HistoriqueDto entityToDto(Historique historique){
        HistoriqueDto dto = new HistoriqueDto();
        dto.setId(historique.getId());
        dto.setEtat(historique.getEtat());
        return dto;
    }
    public List<HistoriqueDto> entityToDto(List<Historique> historiques)
    {
        return historiques.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
