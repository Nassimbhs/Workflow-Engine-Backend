package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ActiviteDto {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private String typeSortie;

    private List<TacheDto> tacheDtoList = new ArrayList<TacheDto>();

}
