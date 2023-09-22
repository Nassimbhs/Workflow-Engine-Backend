package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Competence;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence,Long> {
}
