package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.TacheAtraiterDto;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TacheAtraiterConverter {
    public TacheAtraiterDto entityToDto(TacheAtraiter tacheAtraiter) {
        TacheAtraiterDto dto = new TacheAtraiterDto();
        dto.setId(tacheAtraiter.getId());
        dto.setResponsable(tacheAtraiter.getResponsable());
        dto.setName(tacheAtraiter.getName());
        dto.setDescription(tacheAtraiter.getDescription());
        dto.setEndDate(tacheAtraiter.getEndDate());
        dto.setStatut(tacheAtraiter.getStatut());
        dto.setAction(tacheAtraiter.getAction());
        dto.setApprobation(tacheAtraiter.getApprobation());
        dto.setResponsable(tacheAtraiter.getResponsable());
        return dto;
    }

    public List<TacheAtraiterDto> entityToDto(List<TacheAtraiter> tacheAtraiters) {
        return tacheAtraiters.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
