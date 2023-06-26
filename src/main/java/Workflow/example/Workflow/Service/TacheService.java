package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.*;
import Workflow.example.Workflow.Listener.TacheListener;
import Workflow.example.Workflow.Repository.GroupeUserRepository;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneId;
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
                    a.setAction(tache.getAction());
                    a.setApprobation(tache.getApprobation());
                    tacheRepository.save(a);

                    // Mettre à jour les TacheAtraiter associées à la tâche
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
        List<User> existingUsers = task.getUserList();
        List<User> usersToAdd = userRepository.findAllByIdIn(userIds);
        usersToAdd.removeAll(existingUsers);
        task.getUserList().addAll(usersToAdd);

        tacheRepository.save(task);

        // Ajouter une ligne dans la table TacheAtraiter pour chaque utilisateur ajouté
        for (User user : usersToAdd) {
            TacheAtraiter tacheAtraiter = new TacheAtraiter();
            tacheAtraiter.setName(task.getName());
            tacheAtraiter.setDescription(task.getDescription());
            tacheAtraiter.setCreationDate(new Date());
            // Définir les autres propriétés de TacheAtraiter en fonction de la tâche assignée
            tacheAtraiter.setTacheAtraite(task);
            tacheAtraiter.setResponsable(user.getId());
            tacheAtraiter.setStatut("non traité");
            // Enregistrer la tâche à traiter dans la table TacheAtraiter
            tacheAtraiteRepository.save(tacheAtraiter);
        }

        tacheListener.beforeAssignation(tacheId, userIds);
    }

    @Transactional
    public void desassignerTacheAUtilisateur(Long tacheId, Long userId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new RuntimeException("Tâche non trouvée"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        tache.getUserList().remove(user);
        tacheRepository.save(tache);
        List<TacheAtraiter> tacheAtraiters = tacheAtraiteRepository.findByTacheAtraiteAndResponsable(tache, userId);
        tacheAtraiteRepository.deleteAll(tacheAtraiters);
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

    @Transactional
    public void updateCongeStatut(Long tacheId) {
        Tache tache = tacheRepository.findById(tacheId).orElse(null);
        if (tache != null) {
            Conge conge = tache.getConges().get(0);
            if (conge != null) {
                conge.setStatut("accepter");

                // Créer une nouvelle instance de TacheAtraiter
                TacheAtraiter tacheAtraiter = new TacheAtraiter();
                tacheAtraiter.setName(tache.getName());
                tacheAtraiter.setDescription(tache.getDescription());
                tacheAtraiter.setCreationDate(new Date());
                tacheAtraiter.setStartDate(tache.getStartDate());
                tacheAtraiter.setEndDate(tache.getEndDate());
                tacheAtraiter.setStatut("traité");
                tacheAtraiter.setAction(tache.getAction());
                tacheAtraiter.setApprobation(tache.getApprobation());
                tacheAtraiter.setResponsable(tache.getUserList().get(0).getId());

                tacheAtraiter.setTacheAtraite(tache);
                // Sauvegarder la nouvelle instance de TacheAtraiter
                tacheAtraiteRepository.save(tacheAtraiter);

                tacheRepository.save(tache);
            }
        }
    }

}