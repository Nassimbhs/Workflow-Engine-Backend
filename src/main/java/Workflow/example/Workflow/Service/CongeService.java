package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.example.workflow.entity.Conge;
import workflow.example.workflow.entity.TacheAtraiter;
import workflow.example.workflow.repository.CongeRepository;
import workflow.example.workflow.repository.TacheAtraiteRepository;
import java.util.*;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private TacheAtraiteRepository tacheAtraiteRepository;


    public List<Conge> getAllConge() {
        return congeRepository.findAll();
    }

//    public boolean addCongeWithAssignmentToTacheAtraiter(Conge conge, Long tacheAtraiterId) {
//        TacheAtraiter tacheAtraiter = tacheAtraiteRepository.findById(tacheAtraiterId).orElse(null);
//        if (tacheAtraiter != null) {
//            conge.setTacheAtraiter(tacheAtraiter);
//            tacheAtraiter.getConges().add(conge);
//            tacheAtraiteRepository.save(tacheAtraiter);
//            congeRepository.save(conge);
//            return true;
//        }
//        return false;
//    }
    public boolean addCongeWithAssignmentToTacheAtraiter(Conge conge, Long tacheAtraiterId) {
        TacheAtraiter tacheAtraiter = tacheAtraiteRepository.findById(tacheAtraiterId).orElse(null);
        if (tacheAtraiter != null) {
            if (conge.getTacheAtraiter() != null && conge.getTacheAtraiter().getId().equals(tacheAtraiterId)) {
                return true;
            }
            conge.setTacheAtraiter(tacheAtraiter);
            tacheAtraiter.getConges().add(conge);
            tacheAtraiteRepository.save(tacheAtraiter);
            congeRepository.save(conge);
            return true;
        }
        return false;
    }


}