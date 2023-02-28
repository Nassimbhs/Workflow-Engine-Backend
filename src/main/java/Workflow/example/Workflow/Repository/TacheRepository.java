package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Activite;
import Workflow.example.Workflow.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
}
