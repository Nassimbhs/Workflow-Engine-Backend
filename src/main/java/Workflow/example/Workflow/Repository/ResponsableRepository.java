package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable,Long> {
}