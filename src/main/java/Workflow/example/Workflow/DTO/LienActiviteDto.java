package Workflow.example.Workflow.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class LienActiviteDto implements Serializable {

    private Long id;
    private String source;
    private String target;
    private String workflowId;
    private String activiteSourceName;
    private String activiteTargetName;

}