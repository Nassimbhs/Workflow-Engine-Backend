package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.GroupeUser;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.entity.User;
import workflow.example.workflow.listener.TacheListener;
import workflow.example.workflow.repository.GroupeUserRepository;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TacheService {
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupeUserRepository groupeUserRepository;
    @Autowired
    private TacheAtraiteRepository tacheAtraiteRepository;

    @Autowired
    private TacheListener tacheListener;

    @Transactional
    public ResponseEntity<Object> addTache(Tache tache) {
        Long id = tache.getId();
        if (id != null && tacheRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tache with id " + id + " already exists");
        }

        tache.setCreationDate(new Date());
        tache.setStatut("non traité");
        tache.setApprobation("en attente");

        tacheRepository.save(tache);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Tache", tache);
        responseBody.put("message", "Tache successfully created!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public ResponseEntity<Object> updateTache(Long id, Tache tache) {
        tacheRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setName(tache.getName());
                    a.setDescription(tache.getDescription());
                    a.setCreationDate(tache.getCreationDate());
                    a.setStartDate(tache.getStartDate());
                    a.setEndDate(tache.getEndDate());
                    a.setStatut(tache.getStatut());
                    a.setAction(tache.getAction());
                    a.setApprobation(tache.getApprobation());
                    tacheRepository.save(a);

                    List<TacheAtraiter> tacheAtraiters = a.getTacheAtraiters();
                    for (TacheAtraiter tacheAtraiter : tacheAtraiters) {
                        tacheAtraiter.setName(a.getName());
                        tacheAtraiter.setDescription(a.getDescription());
                        tacheAtraiter.setCreationDate(a.getCreationDate());
                        tacheAtraiter.setStartDate(a.getStartDate());
                        tacheAtraiter.setEndDate(a.getEndDate());
                        tacheAtraiter.setStatut(a.getStatut());
                        tacheAtraiter.setAction(a.getAction());
                        tacheAtraiter.setApprobation(a.getApprobation());
                    }
                    tacheAtraiteRepository.saveAll(tacheAtraiters);

                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !");
                });

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("Tache", tache);
        responseBody.put("message", "Tache successfully updated!");

        return ResponseEntity.ok().body(responseBody);

    }

    @Transactional
    public void deleteTacheById(Long id) {
        Tache tache = tacheRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !"));
        tacheRepository.delete(tache);
    }

    public List<Tache> getAlltaches() {
        return tacheRepository.findAll();
    }

    public Tache findtacheById(Long id) {
        Optional<Tache> tacheOptional = tacheRepository.findById(id);
        if (tacheOptional.isPresent()) {
            return tacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found");
        }
    }

    public List<Tache> findByWorkflowId(Long id) {
        return tacheRepository.findByWorkflowId(id);
    }

    @Transactional
    public void assignerTacheAUtilisateurs(Long tacheId, List<Long> userIds, Long workflowId) {
        Tache task = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        for (Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            if (!user.getTaches().isEmpty()) {
                throw new RuntimeException("L'utilisateur est déjà assigné à une tâche");
            }
        }

        List<User> usersToAdd = userRepository.findAllByIdIn(userIds);
        task.getUserList().addAll(usersToAdd);
        tacheRepository.save(task);

        for (User user : usersToAdd) {
            TacheAtraiter tacheAtraiter = new TacheAtraiter();
            tacheAtraiter.setName(task.getName());
            tacheAtraiter.setDescription(task.getDescription());
            tacheAtraiter.setCreationDate(new Date());
            tacheAtraiter.setAction(task.getAction());
            tacheAtraiter.setTacheAtraite(task);
            tacheAtraiter.setResponsable(user.getId());
            tacheAtraiter.setEmailResponsable(user.getEmail());
            tacheAtraiter.setStatut("non traité");
            tacheAtraiter.setWorkflowId(workflowId);
            tacheAtraiteRepository.save(tacheAtraiter);
        }

        tacheListener.beforeAssignation(tacheId, userIds);
    }

    @Transactional
    public void desassignerTacheAUtilisateur(Long tacheId, Long userId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        tache.getUserList().remove(user);

        tacheRepository.save(tache);

        List<TacheAtraiter> tacheAtraiters = tacheAtraiteRepository.findByTacheAtraiteAndResponsable(tache, userId);
        for (TacheAtraiter tacheAtraiter : tacheAtraiters) {
            tacheAtraiter.getCvs().forEach(cv -> cv.getTachesAtraiter().remove(tacheAtraiter));
            tacheAtraiteRepository.delete(tacheAtraiter);
        }
    }

    public List<User> getUtilisateursDeTache(long tacheId) {
        Tache tache = tacheRepository.findById(tacheId);
        return tache.getUserList();
    }

    public List<Tache> getTasksByUser(Long userId) {
        return tacheRepository.findByUserId(userId);
    }

    public List<Tache> findByUserIdtraite(Long userId) {
        return tacheRepository.findByUserIdtraite(userId);
    }

    @Transactional
    public void assignUsersFromGroupToTask(Long groupId, Long taskId) {
        GroupeUser groupeUser = groupeUserRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Group not found"));
        Tache tache = tacheRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Set<User> users = groupeUser.getUsers();
        tache.getUserList().addAll(users);
        tacheRepository.save(tache);
    }

}