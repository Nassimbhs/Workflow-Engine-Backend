package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class ActiviteService {
    @Autowired
    ActiviteRepository activiteRepository;
    @Transactional
    public ResponseEntity<Object> addActivite(Activite activite) {
        Long id = activite.getId();
        if (id != null && activiteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Activite with id " + id + " already exists");
        }
        activite.setCreationDate(new Date());
        activiteRepository.save(activite);
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("Activite", activite);
                    put("message", "Activite successfully created!");
                }});
    }

    @Transactional
    public ResponseEntity<Object> updateActivite(Long id, Activite activite) {
        activiteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setName(activite.getName());
                    a.setDescription(activite.getDescription());
                    a.setCreationDate(activite.getCreationDate());
                    activiteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activite not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("activite", activite);
                    put("message", "Activite successfully updated!");
                }});    }

    @Transactional
    public void deleteActiviteById(Long id) {
        Activite activite = activiteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activite not found !"));
        activiteRepository.delete(activite);
    }

    public List<Activite> getAllActivites() {
        return (List<Activite>) activiteRepository.findAll();
    }

    public Activite finddActiviteById(Long id) {
        Optional<Activite> activiteOptional = activiteRepository.findById(id);
        if (activiteOptional.isPresent()) {
            return activiteOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activite not found");
        }
    }

    public List<Activite> findByWorkflowId(Long id) {
        return activiteRepository.findByWorkflowId(id);
    }

}
