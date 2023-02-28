package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.TypeDeclencheurConverter;
import Workflow.example.Workflow.DTO.TypeDeclencheurDto;
import Workflow.example.Workflow.Entity.TypeDeclencheur;
import Workflow.example.Workflow.Service.TypeDeclencheurService;
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
@RequestMapping("/api/v1/TypeDeclencheur")
@Tag(name = "TypeDeclencheur", description = "CRUD TypeDeclencheur")
public class TypeDeclencheurController {

    @Autowired
    private TypeDeclencheurService typeDeclencheurService;

    @Autowired
    private TypeDeclencheurConverter typeDeclencheurConverter;

    @PostMapping("/addTypeDeclencheur")
    @Operation(
            summary = "add TypeDeclencheur",
            description = "create TypeDeclencheur.",
            tags = { "TypeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeDeclencheur.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addTypeDeclencheur(@RequestBody TypeDeclencheur typeDeclencheur){
        return typeDeclencheurService.addTypeDeclencheur(typeDeclencheur);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update TypeDeclencheur",
            description = "Update TypeDeclencheur by id.",
            tags = { "TypeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeDeclencheur.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateTypeDeclencheur(@PathVariable Long id, @RequestBody TypeDeclencheur typeDeclencheur) {
        return  typeDeclencheurService.updateDeclencheur(id,typeDeclencheur);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete TypeDeclencheur",
            description = "Delete TypeDeclencheur by id.",
            tags = { "TypeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeDeclencheur.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteTypeDeclencheur(@PathVariable Long id) {
        typeDeclencheurService.deleteTypeDeclencheurById(id);
    }

    @GetMapping("/allTypeDeclencheur/")
    @Operation(
            summary = "Find all TypeDeclencheur",
            description = "Find all TypeDeclencheur.",
            tags = { "TypeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeDeclencheurDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<TypeDeclencheurDto> findAll() {
        return  typeDeclencheurConverter.entityToDto(typeDeclencheurService.getAllTypeDeclencheur());
    }

    @GetMapping("/getTypeDeclencheur/{id}")
    @Operation(
            summary = "Find TypeDeclencheur",
            description = "Find TypeDeclencheur by id.",
            tags = { "TypeDeclencheur" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TypeDeclencheurDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public TypeDeclencheurDto findTypeDeclencheurById(@PathVariable Long id) {
        return typeDeclencheurConverter.entityToDto(typeDeclencheurService.findTypeDeclencheurById(id));
    }

}
