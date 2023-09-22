package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.converter.WorkflowConverter;
import workflow.example.workflow.dto.WorkflowDto;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.service.WorkflowService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Workflow")
@Tag(name = "Workflow", description = "CRUD Workflow")
@CrossOrigin(origins = "http://localhost:4200")
public class WorfklowController {

    @Autowired
    private WorkflowConverter workflowConverter;
    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/addWorkflow")
    @Operation(
            summary = "add workflow",
            description = "create workflow.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Workflow.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addWorkflow(@RequestBody Workflow workflow){
        return workflowService.addWorkflow(workflow);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update workflow",
            description = "Update a workflow by id.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Workflow.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateWorkflow(@PathVariable Long id,@RequestBody Workflow workflow) {
        return  workflowService.updateWorkflow(id,workflow);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Workflow",
            description = "Delete a workflow by id.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Workflow.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteWorkflow(@PathVariable Long id) {
        workflowService.deleteWorkflowById(id);
    }

    @GetMapping("/allWorkflows/")
    @Operation(
            summary = "Find all workflows",
            description = "Find all workflows.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkflowDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<WorkflowDto> findAll() {
        return  workflowConverter.entityToDto(workflowService.getAllWorkflows());
    }

    @GetMapping("/getWorkflow/{id}")
    @Operation(
            summary = "Find workflow",
            description = "Find workflow by id.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkflowDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public WorkflowDto findEmployeeById(@PathVariable Long id) {
        return workflowConverter.entityToDto(workflowService.findWorkflowById(id));
    }

    @GetMapping("/{workflowId}/tables")
    @Operation(
            summary = "Find tables",
            description = "Find tables.",
            tags = { "Workflow" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WorkflowDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<String> getWorkflowTables(@PathVariable Long workflowId) throws NotFoundException {
        return workflowService.getWorkflowTables(workflowId);
    }

}