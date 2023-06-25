package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheAtraiteRepository extends JpaRepository<TacheAtraiter,Long> {
    List<TacheAtraiter> findByTacheAtraiteAndResponsable(Tache tache, Long responsable);
    boolean existsByTacheAtraiteAndResponsable(Tache tache, Long responsable);

}
