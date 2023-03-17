package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.ActiviteDto;
import Workflow.example.Workflow.DTO.LienActiviteDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.LienActivite;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LienActiviteConverter {

    public LienActiviteDto entityToDto(LienActivite lienActivite) {
        LienActiviteDto dto = new LienActiviteDto();
        dto.setId(lienActivite.getId());
        dto.setSource(lienActivite.getSource());
        dto.setTarget(lienActivite.getTarget());
        dto.setWorkflowId(lienActivite.getWorkflowId());
        dto.setActiviteSourceName(lienActivite.getActiviteSourceName());
        dto.setActiviteTargetName(lienActivite.getActiviteTargetName());
        return dto;
    }
    public List<LienActiviteDto> entityToDto(List<LienActivite> lienActivites)
    {
        return lienActivites.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
