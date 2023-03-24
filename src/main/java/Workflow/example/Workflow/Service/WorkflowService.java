package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Workflow;
import Workflow.example.Workflow.Repository.ActiviteRepository;
import Workflow.example.Workflow.Repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    @Transactional
    public ResponseEntity<Object> addWorkflow(Workflow workflow) {
        Long id = workflow.getId();
        if (id != null && workflowRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Workflow with id " + id + " already exists");
        }
        workflow.setCreationDate(new Date());
        workflowRepository.save(workflow);

        // create two default activities for the new workflow
        Activite activity1 = new Activite();
        activity1.setName("Début");
        activity1.setDescription("Default activity 1");
        activity1.setCreationDate(new Date());
        activity1.setWorkflowActivite(workflow);
        activiteRepository.save(activity1);

        Activite activity2 = new Activite();
        activity2.setName("Fin");
        activity2.setDescription("Default activity 2");
        activity2.setCreationDate(new Date());
        activity2.setWorkflowActivite(workflow);
        activiteRepository.save(activity2);

        Map<String, Object> response = new HashMap<>();
        response.put("workflow", workflow);
        response.put("message", "Workflow successfully created!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<Object> updateWorkflow(Long id, Workflow workflow) {
        workflowRepository.findById(id).ifPresentOrElse(
                w -> {
                    w.setName(workflow.getName());
                    w.setDescription(workflow.getDescription());
                    w.setEtat(workflow.getEtat());
                    w.setLastModifiedDate(new Date());
                    workflowRepository.save(w);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("workflow", workflow);
                    put("message", "Workflow successfully updated!");
                }});
    }

    @Transactional
    public void deleteWorkflowById(Long id) {
        Workflow workflow = workflowRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found !"));
        workflowRepository.delete(workflow);
    }

    public List<Workflow> getAllWorkflows() {
        return (List<Workflow>) workflowRepository.findAll();
    }

    public Workflow findWorkflowById(Long id) {
        Optional<Workflow> workflowOptional = workflowRepository.findById(id);
        if (workflowOptional.isPresent()) {
            return workflowOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Workflow not found");
        }
    }
}