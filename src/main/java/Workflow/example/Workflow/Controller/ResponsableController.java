package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.ResponsableConverter;
import Workflow.example.Workflow.DTO.ResponsableDto;
import Workflow.example.Workflow.Entity.Responsable;
import Workflow.example.Workflow.Service.ResponsableService;
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
@RequestMapping("/api/v1/Responsable")
@Tag(name = "Responsable", description = "CRUD Responsable")
public class ResponsableController {


    @Autowired
    private ResponsableService responsableService;
    @Autowired
    private ResponsableConverter responsableConverter;

    @PostMapping("/addResponsable")
    @Operation(
            summary = "add Responsable",
            description = "create Responsable.",
            tags = { "Responsable" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Responsable.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addResponsable(@RequestBody Responsable responsable){
        return responsableService.addResponsable(responsable);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Responsable",
            description = "Update Responsable by id.",
            tags = { "Responsable" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Responsable.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateResponsable(@PathVariable Long id, @RequestBody Responsable responsable) {
        return  responsableService.updateResponsable(id,responsable);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Responsable",
            description = "Delete Responsable by id.",
            tags = { "Responsable" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Responsable.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteResponsable(@PathVariable Long id) {
        responsableService.deleteResponsableById(id);
    }

    @GetMapping("/allResponsable/")
    @Operation(
            summary = "Find all Responsable",
            description = "Find all Responsable.",
            tags = { "Responsable" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponsableDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<ResponsableDto> findAll() {
        return  responsableConverter.entityToDto(responsableService.getAllResponsable());
    }

    @GetMapping("/getResponsable/{id}")
    @Operation(
            summary = "Find Responsable",
            description = "Find Responsable by id.",
            tags = { "Responsable" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponsableDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponsableDto findResponsableById(@PathVariable Long id) {
        return responsableConverter.entityToDto(responsableService.findResponsableById(id));
    }

}
