package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.DTO.LienTacheDto;
import Workflow.example.Workflow.Entity.LienTache;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Repository.TacheRepository;
import Workflow.example.Workflow.Repository.LienTacheRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LienTacheService {

    @Autowired
    private LienTacheRepository lienTacheRepository;
    @Autowired
    private TacheRepository tacheRepository;

    @Transactional
    public ResponseEntity<Object> addLink(LienTache lienTache) {
        // Get the activity corresponding to the given ID
        Optional<Tache> tache = tacheRepository.findById(lienTache.getId());
        if (!tache.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "activity with id " + lienTache.getId() + " not found");
        }
        // Set the activity property of the link to the corresponding activity
        lienTache.setTacheLien(tache.get());

        // Save the link to the repository
        lienTacheRepository.save(lienTache);
        LienTacheDto lienTacheDTO = new LienTacheDto();
        lienTacheDTO.setId(lienTache.getId());
        lienTacheDTO.setSource(lienTache.getSource());
        lienTacheDTO.setTarget(lienTache.getTarget());
        lienTacheDTO.setWorkflowId(lienTache.getWorkflowId());
        lienTacheDTO.setTacheSourceName(lienTache.getTacheSourceName());
        lienTacheDTO.setTacheTargetName(lienTache.getTacheTargetName());
        // Return a JSON response
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("LienActivite", lienTacheDTO);
                    put("message", "Link successfully created!");
                }});
    }
    @Transactional
    public ResponseEntity<Object> updateLink(Long id, LienTache lienTache) {
        lienTacheRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setSource(lienTache.getSource());
                    a.setTarget(lienTache.getTarget());
                    a.setType(lienTache.getType());
                    lienTacheRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("lien", lienTache);
                    put("message", "Link successfully updated!");
                }});    }

    @Transactional
    public void deleteLinkById(Long id) {
        LienTache lienTache = lienTacheRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !"));
        lienTacheRepository.delete(lienTache);
    }

    public List<LienTache> getAllLinks() {
        return (List<LienTache>) lienTacheRepository.findAll();
    }

    public LienTache findLinkById(Long id) {
        Optional<LienTache> lienTacheOptional = lienTacheRepository.findById(id);
        if (lienTacheOptional.isPresent()) {
            return lienTacheOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found");
        }
    }
    public  List<LienTache> findByTacheIdWithTacheLiee(Long activiteId){
        return lienTacheRepository.findByTacheIdWithTacheLiee(activiteId);
    }
}
