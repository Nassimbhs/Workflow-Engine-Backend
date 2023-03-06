package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.LienActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LienActiviteRepository extends JpaRepository<LienActivite,Long> {
}
