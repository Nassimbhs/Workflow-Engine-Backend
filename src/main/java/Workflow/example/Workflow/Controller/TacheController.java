package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.converter.TacheConverter;
import workflow.example.workflow.converter.UserConverter;
import workflow.example.workflow.dto.TacheDto;
import workflow.example.workflow.dto.UserDto;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.service.TacheService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Tache")
@Tag(name = "Tache", description = "CRUD Tache")
@CrossOrigin(origins = "http://localhost:4200")
public class TacheController {

    @Autowired
    private TacheService tacheService;
    @Autowired
    private TacheConverter tacheConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private TacheRepository tacheRepository;

    @PostMapping("/addTache")
    @Operation(
            summary = "add Tache",
            description = "create Tache.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addTache(@RequestBody Tache tache) {
        return tacheService.addTache(tache);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Tache",
            description = "Update Tache by id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return tacheService.updateTache(id, tache);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Tache",
            description = "Delete Tache by id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteTache(@PathVariable Long id) {
        tacheService.deleteTacheById(id);
    }

    @GetMapping("/allTache/")
    @Operation(
            summary = "Find all Tache",
            description = "Find all Tache.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheDto> findAll() {
        return tacheConverter.entityToDto(tacheService.getAlltaches());
    }

    @GetMapping("/getTache/{id}")
    @Operation(
            summary = "Find Tache",
            description = "Find Tache by id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public TacheDto findTacheById(@PathVariable Long id) {
        return tacheConverter.entityToDto(tacheService.findtacheById(id));
    }

    @GetMapping("/taches/{id}")
    @Operation(
            summary = "Find Tache by workflow id",
            description = "Find Tache by workflow id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheDto> findByWorkflowId(@PathVariable Long id) {
        return tacheConverter.entityToDto(tacheService.findByWorkflowId(id));
    }

    @PostMapping("{tacheId}/{workflowId}/assigner-utilisateurs")
    @Operation(
            summary = "Find Tache by workflow id",
            description = "Find Tache by workflow id.",
            tags = { "Tache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> assignerTacheAUtilisateurs(@PathVariable("tacheId") Long tacheId,@PathVariable("workflowId") Long workflowId, @RequestBody List<Long> userIds) {
        tacheService.assignerTacheAUtilisateurs(tacheId,userIds,workflowId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/utilisateurs")
    public List<UserDto> getUtilisateursDeTache(@PathVariable("id") long tacheId) {
        return userConverter.entityToDto(tacheService.getUtilisateursDeTache(tacheId));
    }

    @DeleteMapping("/{tacheId}/utilisateurs/{userId}")
    @Operation(
            summary = "delete user from task",
            description = "delete user from task.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Void> desassignerTacheAUtilisateur(@PathVariable Long tacheId, @PathVariable Long userId) {
        tacheService.desassignerTacheAUtilisateur(tacheId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/tasks")
    @Operation(
            summary = "Find Tache by user id",
            description = "Find Tache by user id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheDto> getTasksByUser(@PathVariable Long userId) {
        return tacheConverter.entityToDto(tacheService.getTasksByUser(userId));
    }

    @GetMapping("/users/{userId}/tachetraite")
    @Operation(
            summary = "Find Tache by user id",
            description = "Find Tache by user id.",
            tags = {"Tache"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TacheDto> findByUserIdtraite(@PathVariable Long userId) {
        return tacheConverter.entityToDto(tacheService.findByUserIdtraite(userId));
    }

    @PostMapping("/tasks/{taskId}/assign/group/{groupId}")
    public void assignGroupToTask(@PathVariable Long groupId, @PathVariable Long taskId) {
        tacheService.assignUsersFromGroupToTask(groupId, taskId);
    }

}