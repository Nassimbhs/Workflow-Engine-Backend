package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.service.TableService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Table", description = "CRUD Table")
@CrossOrigin(origins = "http://localhost:4200")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/tables")
    public List<String> getTables(
            @RequestParam String jdbcUrl,
            @RequestParam String username,
            @RequestParam(required = false) String password,
            @RequestParam String sgbd
    ) {
        return tableService.getTables(jdbcUrl, username, password, sgbd);
    }

}