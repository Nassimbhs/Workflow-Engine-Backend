package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.HistoriqueConverter;
import Workflow.example.Workflow.DTO.HistoriqueDto;
import Workflow.example.Workflow.Service.HistoriqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoriqueController {

    @Autowired
    private HistoriqueService historiqueService;
    @Autowired
    private HistoriqueConverter historiqueConverter;

    @GetMapping("/allHistory/")
    @Operation(
            summary = "Find all History",
            description = "Find all History.",
            tags = { "Historique" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HistoriqueDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<HistoriqueDto> findAll() {
        return historiqueConverter.entityToDto(historiqueService.getAllHistory());
    }

}
