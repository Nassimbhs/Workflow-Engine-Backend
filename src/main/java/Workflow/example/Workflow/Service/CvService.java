package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Cv;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Repository.CvRepository;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CvService {
    @Autowired
    private CvRepository cvRepository;
    @Autowired
    private TacheAtraiteRepository tacheAtraiteRepository;

    public Cv createCV(Cv cv, Long tacheAtraiterId) {
        TacheAtraiter tacheAtraiter = tacheAtraiteRepository.findById(tacheAtraiterId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TacheAtraiter ID: " + tacheAtraiterId));
        cv.getCompetences().forEach(competence -> competence.setCv(cv));
        cv.getFormations().forEach(formation -> formation.setCv(cv));
        cv.getTachesAtraiter().add(tacheAtraiter);
        return cvRepository.save(cv);
    }

    public Optional<Cv> getCvById(Long id) {
        return cvRepository.findById(id);
    }

    public void assignCVToTacheAtraiter(Long cvId, Long tacheAtraiterId) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new EntityNotFoundException("CV not found"));
        TacheAtraiter tacheAtraiter = tacheAtraiteRepository.findById(tacheAtraiterId).orElseThrow(() -> new EntityNotFoundException("TacheAtraiter not found"));

        cv.getTachesAtraiter().add(tacheAtraiter);
        tacheAtraiter.getCvs().add(cv);

        cvRepository.save(cv);
        tacheAtraiteRepository.save(tacheAtraiter);
    }

    public List<Cv> getAllCvs() {
        return cvRepository.findAll();
    }
}
