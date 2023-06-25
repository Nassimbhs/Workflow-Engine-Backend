package Workflow.example.Workflow.Service;
import Workflow.example.Workflow.Entity.Conge;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.Workflow;
import Workflow.example.Workflow.Repository.CongeRepository;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.WorkflowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private WorkflowRepository workflowRepository;

    public void addAndAssignCongeToTask(Conge conge, Long tacheId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new IllegalArgumentException("Tache not found"));
        Long workflowId = tache.getWorkflowTache().getId();
        Workflow workflow = workflowRepository.findById(workflowId).orElseThrow(() -> new IllegalArgumentException("Workflow not found"));
        workflow.setEtat("en cours");
        workflowRepository.save(workflow);

        conge.setTacheConge(tache);
        tache.getConges().add(conge);
        conge.setStatut("en cours");

        tache.setApprobation("accepter");
        tacheRepository.save(tache);
    }

    public List<Conge> getAllConge() {
        return congeRepository.findAll();
    }
    public Conge getCongeByTacheAndUser(Long tacheId, Long userId) {
        return congeRepository.findByTacheCongeIdAndTacheCongeUserListId(tacheId, userId);
    }
    @Transactional
    public void updateCongeApprovalByTacheId(Long tacheId) {
        Optional<Conge> optionalConge = congeRepository.findById(tacheId);
        if (optionalConge.isPresent()) {
            Conge conge = optionalConge.get();
            Tache tache = conge.getTacheConge();
            tache.setApprobation("accepter");

            congeRepository.save(conge);
        } else {
            throw new IllegalArgumentException("Congé non trouvé pour l'ID de tâche spécifié: " + tacheId);
        }
    }

}