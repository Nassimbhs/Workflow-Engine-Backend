package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.converter.LienTacheConverter;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.service.LienTacheService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/LienTache")
@Tag(name = "LienTache", description = "CRUD LienTache")
@CrossOrigin(origins = "http://localhost:4200")
public class LienTacheController {
    @Autowired
    private LienTacheConverter lienTacheConverter;
    @Autowired
    private LienTacheService lienTacheService;

    @PostMapping("/addlink/")
    @Operation(
            summary = "add link",
            description = "create link.",
            tags = { "LienTache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addlink(@RequestBody LienTache lienTache){
        return lienTacheService.addLink(lienTache);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update link",
            description = "Update a link by id.",
            tags = { "LienTache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateLink(@PathVariable Long id, @RequestBody LienTache lienTache) {
        return  lienTacheService.updateLink(id,lienTache);
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
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTache.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteLink(@PathVariable Long id) {
        lienTacheService.deleteLinkById(id);
    }

    @GetMapping("/allLinks/")
    @Operation(
            summary = "Find all links",
            description = "Find all links.",
            tags = { "LienTache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<LienTacheDto> findAll() {
        return  lienTacheConverter.entityToDto(lienTacheService.getAllLinks());
    }

    @GetMapping("/getLink/{id}")
    @Operation(
            summary = "Find link",
            description = "Find link by id.",
            tags = { "LienTache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public LienTacheDto findLinkById(@PathVariable Long id) {
        return lienTacheConverter.entityToDto(lienTacheService.findLinkById(id));
    }



    @GetMapping("/getLinkTache/{id}")
    @Operation(
            summary = "Find link",
            description = "Find link by id.",
            tags = { "LienTache" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LienTacheDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<LienTacheDto> findByTacheIdWithTacheLiee(@PathVariable Long id) {
        return lienTacheConverter.entityToDto(lienTacheService.findByTacheIdWithTacheLiee(id));
    }

}