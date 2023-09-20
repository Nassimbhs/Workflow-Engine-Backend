package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Cv;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Repository.CvRepository;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import jakarta.transaction.Transactional;
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
        cv.getInterets().forEach(interet -> interet.setCv(cv));
        cv.getLangues().forEach(langue -> langue.setCv(cv));
        cv.getExperiences().forEach(experience -> experience.setCv(cv));

        cv.getTachesAtraiter().add(tacheAtraiter);
        return cvRepository.save(cv);
    }

    public Optional<Cv> getCvById(Long id) {
        return cvRepository.findById(id);
    }

    @Transactional
    public Cv assignCvToTacheAtraiter(Long cvId, Long tacheAtraiterId) {
        Cv cv = cvRepository.findById(cvId).orElse(null);
        TacheAtraiter tacheAtraiter = tacheAtraiteRepository.findById(tacheAtraiterId).orElse(null);

        if (cv != null && tacheAtraiter != null) {
            cv.getTachesAtraiter().add(tacheAtraiter);
            tacheAtraiter.getCvs().add(cv);
            cvRepository.save(cv);
            tacheAtraiteRepository.save(tacheAtraiter);
        }
        return cv;
    }

    public List<Cv> getAllCvs() {
        return cvRepository.findAll();
    }
    public List<Cv> getCvByTacheAtraiterId(Long tacheAtraiterId) {
        return cvRepository.findByTachesAtraiterId(tacheAtraiterId);
    }
    public Cv getCvWithCompetencesAndFormations(Long tacheAtraiterId) {
        return cvRepository.findByIdWithCompetencesAndFormations(tacheAtraiterId);
    }

}
