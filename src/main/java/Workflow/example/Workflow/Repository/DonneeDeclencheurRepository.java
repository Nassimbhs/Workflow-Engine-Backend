package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.DonneeDeclencheur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneeDeclencheurRepository extends JpaRepository<DonneeDeclencheur,Long> {
}
