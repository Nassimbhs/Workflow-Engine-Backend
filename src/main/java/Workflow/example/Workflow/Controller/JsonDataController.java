package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.JsonDataConverter;
import Workflow.example.Workflow.DTO.JsonDataDto;
import Workflow.example.Workflow.Entity.JsonData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Workflow.example.Workflow.Service.JsonDataService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/JsonData")
@Tag(name = "JsonData", description = "CRUD JsonData")
@CrossOrigin(origins = "http://localhost:4200")
public class JsonDataController {

    private final JsonDataService jsonDataService;
    private final JsonDataConverter jsonDataConverter;

    @Autowired
    public JsonDataController
            (
                    JsonDataService jsonDataService,
                    JsonDataConverter jsonDataConverter
            )
    {
        this.jsonDataService = jsonDataService;
        this.jsonDataConverter = jsonDataConverter;
    }

    @PostMapping("/add-json-data/{responsable}")
    public ResponseEntity<String> addJsonDataAndAssociateTaches(
            @PathVariable Long responsable,
            @RequestBody String jsonData) {
        jsonDataService.addJsonDataAndAssociateTaches(responsable, jsonData);
        return ResponseEntity.ok("JsonData added and associated with TacheAtraiter entities.");
    }

    @GetMapping("/jsonData/{tacheAtraiterId}")
    public List<JsonDataDto> getJsonDataByTacheAtraiterId(@PathVariable Long tacheAtraiterId) {
        return jsonDataConverter.entityToDto(jsonDataService.getJsonDataByTacheAtraiterId(tacheAtraiterId));
    }

    @PutMapping("/update-state/{id}")
    public ResponseEntity<String> updateStateToTreated(@PathVariable Long id) {
        JsonData updatedJsonData = jsonDataService.updateStateToTreated(id);
        if (updatedJsonData != null) {
            return ResponseEntity.ok("JsonData state updated to 'traité'");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("findById/{id}")
    public JsonDataDto findById(@PathVariable Long id) {
        return jsonDataConverter.entityToDto(jsonDataService.findById(id));
    }

}