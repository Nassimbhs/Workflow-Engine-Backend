package Workflow.example.Workflow.DTO;

import lombok.Data;

@Data

public class TacheDto {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String etat;
    private String priorite;

}
