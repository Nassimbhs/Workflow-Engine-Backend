package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Responsable;
import Workflow.example.Workflow.Repository.ActiviteRepository;
import Workflow.example.Workflow.Repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsableService {
    @Autowired
    private ResponsableRepository responsableRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    @Transactional
    public ResponseEntity<Object> addResponsable(Responsable responsable) {
        activiteRepository
                .findById(responsable.getId())
                .ifPresentOrElse(
                        x -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable id already exit !");
                        },
                        () -> {
                            responsableRepository.save(responsable);
                        });

        return ResponseEntity.accepted().body("Responsable successfully Created !");
    }

    @Transactional
    public ResponseEntity<Object> updateResponsable(Long id, Responsable responsable) {
        responsableRepository.findById(id).ifPresentOrElse(
                r -> {
                    r.setUsername(responsable.getUsername());
                    r.setPassword(responsable.getPassword());
                    responsableRepository.save(r);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable not found !");
                });
        return ResponseEntity.accepted().body("Responsable successfully updated !");
    }

    @Transactional
    public void deleteResponsableById(Long id) {
        Responsable responsable = responsableRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable not found !"));
        responsableRepository.delete(responsable);
    }

    public List<Responsable> getAllResponsable() {
        return (List<Responsable>) responsableRepository.findAll();
    }

    public Responsable findResponsableById(Long id) {
        Optional<Responsable> responsableOptional = responsableRepository.findById(id);
        if (responsableOptional.isPresent()) {
            return responsableOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable not found !");
        }
    }

}
