package Workflow.example.Workflow.Service;
import Workflow.example.Workflow.Entity.Conge;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Repository.CongeRepository;
import Workflow.example.Workflow.Repository.TacheAtraiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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