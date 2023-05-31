package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Historique;
import Workflow.example.Workflow.Repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueService {

    @Autowired
    private HistoriqueRepository historiqueRepository;

    public List<Historique> getAllHistory() {
        return historiqueRepository.findAll();
    }

}
