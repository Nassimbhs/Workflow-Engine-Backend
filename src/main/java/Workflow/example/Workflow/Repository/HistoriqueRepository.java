package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique,Long> {
}
