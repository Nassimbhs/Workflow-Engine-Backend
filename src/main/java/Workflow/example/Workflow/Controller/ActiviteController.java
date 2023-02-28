package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.ActiviteConverter;
import Workflow.example.Workflow.DTO.ActiviteDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Service.ActiviteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Activite")
@Tag(name = "Activite", description = "CRUD Activite")
@CrossOrigin(origins = "http://localhost:4200")
public class ActiviteController {

    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private ActiviteConverter activiteConverter;

    @PostMapping("/addActivite")
    @Operation(
            summary = "add Activite",
            description = "create Activite.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addActivite(@RequestBody Activite activite){
        return activiteService.addActivite(activite);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Activite",
            description = "Update Activite by id.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateActivite(@PathVariable Long id, @RequestBody Activite activite) {
        return  activiteService.updateActivite(id,activite);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Activite",
            description = "Delete Activite by id.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Activite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteActivite(@PathVariable Long id) {
        activiteService.deleteActiviteById(id);
    }

    @GetMapping("/allActivite/")
    @Operation(
            summary = "Find all Activite",
            description = "Find all Activite.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActiviteDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<ActiviteDto> findAll() {
        return  activiteConverter.entityToDto(activiteService.getAllActivites());
    }

    @GetMapping("/getActivite/{id}")
    @Operation(
            summary = "Find Activite",
            description = "Find Activite by id.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActiviteDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ActiviteDto findActiviteById(@PathVariable Long id) {
        return activiteConverter.entityToDto(activiteService.finddActiviteById(id));
    }

    @GetMapping("/activites/{id}")
    @Operation(
            summary = "Find Activite by workflow id",
            description = "Find Activite by workflow id.",
            tags = { "Activite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActiviteDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )

    public List<ActiviteDto> findByWorkflowId(@PathVariable Long id) {
        return activiteConverter.entityToDto(activiteService.findByWorkflowId(id));
    }
}