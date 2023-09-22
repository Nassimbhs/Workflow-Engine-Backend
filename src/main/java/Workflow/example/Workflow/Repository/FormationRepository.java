package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
}
