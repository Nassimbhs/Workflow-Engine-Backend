package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.CongeConverter;
import Workflow.example.Workflow.DTO.CongeDto;
import Workflow.example.Workflow.Entity.Conge;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Service.CongeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Conge")
@Tag(name = "Conge", description = "CRUD Conge")
@CrossOrigin(origins = "http://localhost:4200")
public class CongeController {

    @Autowired
    private CongeService congeService;
    @Autowired
    private CongeConverter congeConverter;

    @GetMapping("/getAllConge/")
    @Operation(
            summary = "Find all Conge",
            description = "Find all Conge.",
            tags = { "Conge" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongeDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<CongeDto> findAll() {
        return congeConverter.entityToDto(congeService.getAllConge());
    }

    @PostMapping("/conges/taches/{tacheId}")
    public ResponseEntity<String> addAndAssignCongeToTask(@RequestBody Conge conge, @PathVariable Long tacheId) {
        congeService.addAndAssignCongeToTask(conge, tacheId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Conge added and assigned to Tache successfully");
    }

    @GetMapping("/byTask/{tacheId}/{userId}")
    @Operation(
            summary = "Get conge by task",
            description = "Get conge by task.",
            tags = { "Conge" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CongeDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<CongeDto> getCongeByTacheAndUser(@PathVariable Long tacheId, @PathVariable Long userId) {
        CongeDto congeDto = congeConverter.entityToDto(congeService.getCongeByTacheAndUser(tacheId, userId));
        if (congeDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(congeDto);
    }
    @PutMapping("/{tacheId}/approbation")
    @Operation(
            summary = "Accept task",
            description = "Update and accept task.",
            tags = { "Conge" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Conge.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void updateCongeApprovalByTacheId(@PathVariable Long tacheId) {
        congeService.updateCongeApprovalByTacheId(tacheId);
    }

}