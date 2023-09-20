package Workflow.example.Workflow.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class JsonDataDto {

    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String data;
    private Long responsable;
    private String etat;

}
