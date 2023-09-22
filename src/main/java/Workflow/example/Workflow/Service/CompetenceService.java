package workflow.example.workflow.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.example.workflow.entity.Competence;
import workflow.example.workflow.repository.CompetenceRepository;

import java.util.List;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> fetchCompetences() {
        return competenceRepository.findAll();
    }
}
