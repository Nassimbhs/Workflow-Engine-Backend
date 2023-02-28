package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.TacheConverter;
import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Service.TacheService;
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
@RequestMapping("/api/v1/Tache")
@Tag(name = "Tache", description = "CRUD Tache")
public class TacheController {


    @Autowired
    private TacheService tacheService;
    @Autowired
    private TacheConverter tacheConverter;

    @PostMapping("/addTache")
    @Operation(
            summary = "add Tache",
            description = "create Tache.",
            tags = { "Tache" },
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
    public ResponseEntity<Object> addTache(@RequestBody Tache tache){
        return tacheService.addTache(tache);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Tache",
            description = "Update Tache by id.",
            tags = { "Tache" },
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
        return  tacheService.updateTache(id,tache);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Tache",
            description = "Delete Tache by id.",
            tags = { "Tache" },
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
    public List<TacheDto> findAll() {
        return  tacheConverter.entityToDto(tacheService.getAllTache());
    }

    @GetMapping("/getTache/{id}")
    @Operation(
            summary = "Find Tache",
            description = "Find Tache by id.",
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
    public TacheDto findTacheById(@PathVariable Long id) {
        return tacheConverter.entityToDto(tacheService.findTacheById(id));
    }

}
