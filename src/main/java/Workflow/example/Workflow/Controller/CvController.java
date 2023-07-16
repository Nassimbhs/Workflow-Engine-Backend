package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.CvConverter;
import Workflow.example.Workflow.DTO.CvDto;
import Workflow.example.Workflow.Entity.Cv;
import Workflow.example.Workflow.Service.CvService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Cv")
@Tag(name = "Cv", description = "CRUD Cv")
@CrossOrigin(origins = "http://localhost:4200")
public class CvController {
    @Autowired
    private CvService cvService;
    @Autowired
    private CvConverter cvConverter;

    @PostMapping("/{tacheAtraiterId}")
    public ResponseEntity<CvDto> createCv(@RequestBody Cv cv, @PathVariable Long tacheAtraiterId) {
        Cv createdCv = cvService.createCV(cv, tacheAtraiterId);
        CvDto cvDto = cvConverter.entityToDto(createdCv);
        return ResponseEntity.ok(cvDto);
    }

    @GetMapping("/getCv/{id}")
    public ResponseEntity<CvDto> getCvById(@PathVariable Long id) {
        Cv cv = cvService.getCvById(id)
                .orElse(null);
        if (cv != null) {
            CvDto cvDto = cvConverter.entityToDto(cv);
            return ResponseEntity.ok(cvDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cvId}/assign-tache/{tacheAtraiterId}")
    public ResponseEntity<String> assignCvToTacheAtraiter(
            @PathVariable("cvId") Long cvId,
            @PathVariable("tacheAtraiterId") Long tacheAtraiterId) {
        cvService.assignCVToTacheAtraiter(cvId, tacheAtraiterId);
        return new ResponseEntity<>("CV assigned to TacheAtraiter successfully", HttpStatus.OK);
    }

    @GetMapping("/getAllCvs")
    public List<CvDto> getAllCvs() {
        return cvConverter.entityToDto(cvService.getAllCvs());
    }
}
