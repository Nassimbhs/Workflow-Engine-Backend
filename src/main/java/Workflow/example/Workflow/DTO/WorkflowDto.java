package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class WorkflowDto {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;
    private String etat;
    private String declencheur;

    private List<TypeDeclencheurDto> typeDeclencheurDtoList = new ArrayList<TypeDeclencheurDto>();
    private List<ActiviteDto> activiteDtoList = new ArrayList<ActiviteDto>();
    private List<ResponsableDto> responsableDtoList = new ArrayList<ResponsableDto>();

}
