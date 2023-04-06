package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.WorkflowDto;
import Workflow.example.Workflow.Entity.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowConverter {
    @Autowired
    private TacheConverter tacheConverter;

    public WorkflowDto entityToDto(Workflow workflow){

        WorkflowDto dto = new WorkflowDto();
        dto.setId(workflow.getId());
        dto.setName(workflow.getName());
        dto.setDescription(workflow.getDescription());
        dto.setEtat(workflow.getEtat());
        dto.setCreationDate(workflow.getCreationDate());
        dto.setLastModifiedDate(workflow.getLastModifiedDate());
        dto.setDeclencheur(workflow.getDeclencheur());
        dto.setTacheDtoList(tacheConverter.entityToDto(workflow.getTaches()));
        return dto;
    }

    public List<WorkflowDto> entityToDto(List<Workflow> workflows)
    {
        return workflows.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
