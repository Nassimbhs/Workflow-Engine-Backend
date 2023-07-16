package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
}
