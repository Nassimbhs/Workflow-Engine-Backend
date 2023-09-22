package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.dto.LienTacheDto;
import workflow.example.workflow.entity.LienTache;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.repository.LienTacheRepository;
import workflow.example.workflow.repository.TacheRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LienTacheService {

    @Autowired
    private LienTacheRepository lienTacheRepository;
    @Autowired
    private TacheRepository tacheRepository;

    @Transactional
    public ResponseEntity<Object> addLink(LienTache lienTache) {
        Optional<Tache> tache = tacheRepository.findById(lienTache.getId());
        if (!tache.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "activity with id " + lienTache.getId() + " not found");
        }
        lienTache.setTacheLien(tache.get());

        lienTacheRepository.save(lienTache);
        LienTacheDto lienTacheDTO = new LienTacheDto();
        lienTacheDTO.setId(lienTache.getId());
        lienTacheDTO.setSource(lienTache.getSource());
        lienTacheDTO.setTarget(lienTache.getTarget());
        lienTacheDTO.setWorkflowId(lienTache.getWorkflowId());
        lienTacheDTO.setTacheSourceName(lienTache.getTacheSourceName());
        lienTacheDTO.setTacheTargetName(lienTache.getTacheTargetName());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("LienActivite", lienTacheDTO);
        responseBody.put("message", "Link successfully created!");

        return ResponseEntity.ok().body(responseBody);

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
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("LienActivite", lienTache);
        responseBody.put("message", "Link successfully updated !");

        return ResponseEntity.ok().body(responseBody);
    }

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
