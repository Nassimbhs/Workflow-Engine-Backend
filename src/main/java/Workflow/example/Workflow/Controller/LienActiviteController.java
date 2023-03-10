package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.LienActiviteConverter;
import Workflow.example.Workflow.DTO.LienActiviteDto;
import Workflow.example.Workflow.Entity.LienActivite;
import Workflow.example.Workflow.Service.LienActiviteService;
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
@RequestMapping("/api/v1/LienActivite")
@Tag(name = "LienActivite", description = "CRUD LienActivite")
@CrossOrigin(origins = "http://localhost:4200")
public class LienActiviteController {
    @Autowired
    private LienActiviteConverter lienActiviteConverter;
    @Autowired
    private LienActiviteService lienActiviteService;

    @PostMapping("/addlink/")
    @Operation(
            summary = "add link",
            description = "create link.",
            tags = { "LienActivite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienActivite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addlink(@RequestBody LienActivite lienActivite){
        return lienActiviteService.addLink(lienActivite);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update link",
            description = "Update a link by id.",
            tags = { "LienActivite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienActivite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateLink(@PathVariable Long id, @RequestBody LienActivite lienActivite) {
        return  lienActiviteService.updateLink(id,lienActivite);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Link",
            description = "Delete a link by id.",
            tags = { "Link" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienActivite.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteLink(@PathVariable Long id) {
        lienActiviteService.deleteLinkById(id);
    }

    @GetMapping("/allLinks/")
    @Operation(
            summary = "Find all links",
            description = "Find all links.",
            tags = { "LienActivite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienActiviteDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<LienActiviteDto> findAll() {
        return  lienActiviteConverter.entityToDto(lienActiviteService.getAllLinks());
    }

    @GetMapping("/getLink/{id}")
    @Operation(
            summary = "Find link",
            description = "Find link by id.",
            tags = { "LienActivite" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienActiviteDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public LienActiviteDto findLinkById(@PathVariable Long id) {
        return lienActiviteConverter.entityToDto(lienActiviteService.findLinkById(id));
    }

}
