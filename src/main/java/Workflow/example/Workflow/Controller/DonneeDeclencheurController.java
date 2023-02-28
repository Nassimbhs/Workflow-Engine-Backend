package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.DonneDeclencheurConverter;
import Workflow.example.Workflow.DTO.DonneeDeclencheurDto;
import Workflow.example.Workflow.Entity.DonneeDeclencheur;
import Workflow.example.Workflow.Service.DonneeDeclencheurService;
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
@RequestMapping("/api/v1/DonneeDeclencheur")
@Tag(name = "DonneeDeclencheur", description = "CRUD DonneeDeclencheur")
public class DonneeDeclencheurController {


    @Autowired
    private DonneeDeclencheurService donneeDeclencheurService;
    @Autowired
    private DonneDeclencheurConverter donneDeclencheurConverter;

    @PostMapping("/addDonneeDeclencheur")
    @Operation(
            summary = "add DonneeDeclencheur",
            description = "create DonneeDeclencheur.",
            tags = { "DonneeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DonneeDeclencheur.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addDonneeDeclencheur(@RequestBody DonneeDeclencheur donneeDeclencheur){
        return donneeDeclencheurService.addDonneeDeclencheur(donneeDeclencheur);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update donneeDeclencheur",
            description = "Update donneeDeclencheur by id.",
            tags = { "donneeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DonneeDeclencheur.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateDonneeDeclencheur(@PathVariable Long id, @RequestBody DonneeDeclencheur donneeDeclencheur) {
        return  donneeDeclencheurService.updateDonneeDeclencheur(id,donneeDeclencheur);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete donneeDeclencheur",
            description = "Delete donneeDeclencheur by id.",
            tags = { "donneeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DonneeDeclencheurController.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteDonneeDeclencheur(@PathVariable Long id) {
        donneeDeclencheurService.deleteDonneeDeclencheurById(id);
    }

    @GetMapping("/allDonneeDeclencheur/")
    @Operation(
            summary = "Find all DonneeDeclencheur",
            description = "Find all DonneeDeclencheur.",
            tags = { "DonneeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DonneeDeclencheurDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<DonneeDeclencheurDto> findAll() {
        return  donneDeclencheurConverter.entityToDto(donneeDeclencheurService.getAllDonneeDeclencheur());
    }

    @GetMapping("/getDonneeDeclencheur/{id}")
    @Operation(
            summary = "Find DonneeDeclencheur",
            description = "Find DonneeDeclencheur by id.",
            tags = { "DonneeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DonneeDeclencheurDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public DonneeDeclencheurDto finddonneeDeclencheurById(@PathVariable Long id) {
        return donneDeclencheurConverter.entityToDto(donneeDeclencheurService.findDonneeDeclencheurById(id));
    }


}
