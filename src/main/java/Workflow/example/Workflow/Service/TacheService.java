package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Converter.TacheConverter;
import Workflow.example.Workflow.Entity.GroupeUser;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Repository.GroupeUserRepository;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TacheService {
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupeUserRepository groupeUserRepository;
    @Transactional
    public ResponseEntity<Object> addTache(Tache tache) {
        Long id = tache.getId();
        if (id != null && tacheRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tache with id " + id + " already exists");
        }
        tache.setCreationDate(new Date());
        tache.setStatut("non traité");
        tacheRepository.save(tache);
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("Tache", tache);
                    put("message", "Tache successfully created!");
                }});
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
                    a.setTriggerType(tache.getTriggerType());
                    tacheRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("tache", tache);
                    put("message", "Tache successfully updated!");
                }});
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
    public void assignerTacheAUtilisateurs(Long tacheId, List<Long> userIds) {
        Tache task = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        List<User> existingUsers = task.getUserList(); // Get the existing users assigned to the task

        List<User> usersToAdd = userRepository.findAllByIdIn(userIds);

        usersToAdd.removeAll(existingUsers); // Remove existing users from the new users list

        task.getUserList().addAll(usersToAdd); // Add the new users to the task

        tacheRepository.save(task);
    }

    @Transactional
    public void desassignerTacheAUtilisateur(Long tacheId, Long userId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        tache.getUserList().remove(user);

        tacheRepository.save(tache);
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