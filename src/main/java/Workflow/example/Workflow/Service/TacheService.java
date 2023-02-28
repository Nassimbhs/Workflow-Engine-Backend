package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TacheService {


    @Autowired
    private TacheRepository tacheRepository;

    @Transactional
    public ResponseEntity<Object> addTache(Tache tache) {
        tacheRepository
                .findById(tache.getId())
                .ifPresentOrElse(
                        x -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache id already exit !");
                        },
                        () -> {
                            tacheRepository.save(tache);
                        });

        return ResponseEntity.accepted().body("Tache successfully Created !");
    }

    @Transactional
    public ResponseEntity<Object> updateTache(Long id, Tache tache) {
        tacheRepository.findById(id).ifPresentOrElse(
                t -> {
                    t.setName(tache.getName());
                    t.setEtat(tache.getEtat());
                    t.setStartDate(tache.getStartDate());
                    t.setEndDate(tache.getEndDate());
                    t.setPriorite(tache.getPriorite());

                    tacheRepository.save(t);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !");
                });
        return ResponseEntity.accepted().body("Tache successfully updated !");
    }

    @Transactional
    public void deleteTacheById(Long id) {
        Tache tache = tacheRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !"));
        tacheRepository.delete(tache);
    }

    public List<Tache> getAllTache() {
        return (List<Tache>) tacheRepository.findAll();
    }

    public Tache findTacheById(Long id) {
        Optional<Tache> tacheOptional = tacheRepository.findById(id);
        if (tacheOptional.isPresent()) {
            return tacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found !");
        }
    }

}
