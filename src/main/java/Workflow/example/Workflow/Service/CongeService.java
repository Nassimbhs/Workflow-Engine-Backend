package Workflow.example.Workflow.Service;
import Workflow.example.Workflow.Entity.Conge;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Entity.Workflow;
import Workflow.example.Workflow.Repository.CongeRepository;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.WorkflowRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private WorkflowRepository workflowRepository;
    @Autowired
    private TacheAtraiteRepository tacheAtraiteRepository;

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
        // Create a new TacheAtraiter instance
        TacheAtraiter tacheAtraiter = new TacheAtraiter();
        tacheAtraiter.setName(tache.getName());
        tacheAtraiter.setDescription(tache.getDescription());
        tacheAtraiter.setCreationDate(new Date());
        tacheAtraiter.setStartDate(tache.getStartDate());
        tacheAtraiter.setEndDate(tache.getEndDate());
        tacheAtraiter.setStatut("traité");
        tacheAtraiter.setAction(tache.getAction());
        tacheAtraiter.setApprobation(tache.getApprobation());
        tacheAtraiter.setTacheAtraite(tache);

        // Save the TacheAtraiter instance
        tacheAtraiteRepository.save(tacheAtraiter);

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