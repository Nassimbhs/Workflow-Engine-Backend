package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.JsonDataDto;
import Workflow.example.Workflow.Entity.JsonData;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonDataConverter {

    public JsonDataDto entityToDto(JsonData jsonData){
        JsonDataDto dto = new JsonDataDto();
        dto.setId(jsonData.getId());
        dto.setData(jsonData.getData());
        dto.setResponsable(jsonData.getResponsable());
        dto.setEtat(jsonData.getEtat());
        return dto;
    }
    public List<JsonDataDto> entityToDto(List<JsonData> jsonData)
    {
        return jsonData.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
