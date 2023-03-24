package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.DTO.LienActiviteDto;
import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.LienActivite;
import Workflow.example.Workflow.Repository.ActiviteRepository;
import Workflow.example.Workflow.Repository.LienActiviteRepository;
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
public class LienActiviteService {

    @Autowired
    LienActiviteRepository lienActiviteRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    @Transactional
    public ResponseEntity<Object> addLink(LienActivite lienActivite) {
        // Get the activity corresponding to the given ID
        Optional<Activite> activite = activiteRepository.findById(lienActivite.getId());
        if (!activite.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "activity with id " + lienActivite.getId() + " not found");
        }
        // Set the activity property of the link to the corresponding activity
        lienActivite.setActiviteLien(activite.get());

        // Save the link to the repository
        lienActiviteRepository.save(lienActivite);
        LienActiviteDto lienActiviteDTO = new LienActiviteDto();
        lienActiviteDTO.setId(lienActivite.getId());
        lienActiviteDTO.setSource(lienActivite.getSource());
        lienActiviteDTO.setTarget(lienActivite.getTarget());
        lienActiviteDTO.setWorkflowId(lienActivite.getWorkflowId());
        lienActiviteDTO.setActiviteSourceName(lienActivite.getActiviteSourceName());
        lienActiviteDTO.setActiviteTargetName(lienActivite.getActiviteTargetName());
        // Return a JSON response
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("LienActivite", lienActiviteDTO);
                    put("message", "Link successfully created!");
                }});
    }
    @Transactional
    public ResponseEntity<Object> updateLink(Long id, LienActivite lienActivite) {
        lienActiviteRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setSource(lienActivite.getSource());
                    a.setTarget(lienActivite.getTarget());
                    a.setType(lienActivite.getType());
                    lienActiviteRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("lien", lienActivite);
                    put("message", "Link successfully updated!");
                }});    }

    @Transactional
    public void deleteLinkById(Long id) {
        LienActivite lienActivite = lienActiviteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found !"));
        lienActiviteRepository.delete(lienActivite);
    }

    public List<LienActivite> getAllLinks() {
        return (List<LienActivite>) lienActiviteRepository.findAll();
    }

    public LienActivite findLinkById(Long id) {
        Optional<LienActivite> lienActiviteOptional = lienActiviteRepository.findById(id);
        if (lienActiviteOptional.isPresent()) {
            return lienActiviteOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link not found");
        }
    }
    public  List<LienActivite> findByActiviteIdWithActiviteLiee(Long activiteId){
        return lienActiviteRepository.findByActiviteIdWithActiviteLiee(activiteId);
    }
}
