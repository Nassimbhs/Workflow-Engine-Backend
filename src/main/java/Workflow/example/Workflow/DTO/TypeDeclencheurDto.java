package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TypeDeclencheurDto {

    private Long id;
    private String type;
    private List<DonneeDeclencheurDto> donneeDeclencheurDtoList = new ArrayList<DonneeDeclencheurDto>();

}
