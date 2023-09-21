package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TacheAtraiteService {

    @Autowired
    private TacheAtraiteRepository tacheAtraiteRepository;

    public List<TacheAtraiter> getTacheAtraiterByResponsable(Long responsableId) {
        return tacheAtraiteRepository.findByResponsable(responsableId);
    }

    @Transactional
    public ResponseEntity<Object> marquerTacheCommeTraite(Long id, TacheAtraiter tacheAtraiter) {
        tacheAtraiteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setStatut("traité");
                    a.setApprobation("Accepter");
                    tacheAtraiteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found !");
                });

        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("tacheAtraiter", tacheAtraiter);
                    put("message", "tacheAtraiter successfully updated!");
                }});
    }

    @Transactional
    public ResponseEntity<Object> RejeterTache(Long id, TacheAtraiter tacheAtraiter) {
        tacheAtraiteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setStatut("traité");
                    a.setApprobation("Rejeter");
                    tacheAtraiteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found !");
                });

        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("tacheAtraiter", tacheAtraiter);
                    put("message", "tacheAtraiter successfully updated!");
                }});
    }

    public List<TacheAtraiter> getAlltachesAtraiter() {
        return tacheAtraiteRepository.findAll();
    }

    public TacheAtraiter findtacheById(Long id) {
        Optional<TacheAtraiter> tacheOptional = tacheAtraiteRepository.findById(id);
        if (tacheOptional.isPresent()) {
            return tacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TacheAtraiter not found");
        }
    }

    public List<TacheAtraiter> getTachesTraiteesParResponsable(Long responsableId) {
        return tacheAtraiteRepository.getTachesTraiteesParResponsable(responsableId);
    }

}
