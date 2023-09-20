package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangueRepository extends JpaRepository<Langue,Long> {
}
