package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Competence;
import Workflow.example.Workflow.Repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> fetchCompetences() {
        return competenceRepository.findAll();
    }
}
