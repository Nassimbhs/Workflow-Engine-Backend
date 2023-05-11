package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
    @Query("SELECT a FROM Tache a WHERE a.workflowTache.id = :id")
    List<Tache> findByWorkflowId(@Param("id") Long id);
    Tache findById(long id);
    @Query("select t from Tache t join t.userList u where u.id = :id")
    List<Tache> findByUserId(@Param("id") Long id);

}