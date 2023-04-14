package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Converter.TacheConverter;
import Workflow.example.Workflow.DTO.TacheDto;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class TacheService {
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TacheConverter tacheConverter;
    @Transactional
    public ResponseEntity<Object> addTache(Tache tache) {
        Long id = tache.getId();
        if (id != null && tacheRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tache with id " + id + " already exists");
        }
        tache.setCreationDate(new Date());
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

    public void assignerTacheAUtilisateurs(Long tacheId, List<Long> userIds) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new EntityNotFoundException("La tâche avec l'id " + tacheId + " n'existe pas"));

        List<User> users = userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new EntityNotFoundException("Un ou plusieurs des utilisateurs n'existent pas");
        }
        for (User user : users) {
            if (!user.getTaches().contains(tache)) {
                user.getTaches().add(tache);
                userRepository.save(user);
            }
        }
    }

    public void desassignerTacheAUtilisateur(Long tacheId, Long userId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new EntityNotFoundException("La tâche avec l'id " + tacheId + " n'existe pas"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("L'utilisateur avec l'id " + userId + " n'existe pas"));

        if (user.getTaches().remove(tache)) {
            userRepository.save(user);
        }
    }

    public List<User> getUtilisateursDeTache(long tacheId) {
        Tache tache = tacheRepository.findById(tacheId);
        return tache.getUserList();
    }

}