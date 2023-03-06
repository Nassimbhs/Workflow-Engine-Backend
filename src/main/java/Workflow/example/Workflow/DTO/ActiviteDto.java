package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ActiviteDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private String target;

    private List<TacheDto> tacheDtoList = new ArrayList<TacheDto>();
    private List<LienActiviteDto> lienActiviteDtos = new ArrayList<LienActiviteDto>();

}
