package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence,Long> {
}
