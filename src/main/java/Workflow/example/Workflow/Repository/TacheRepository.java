package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Tache;

import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
    @Query("SELECT a FROM Tache a WHERE a.workflowTache.id = :id")
    List<Tache> findByWorkflowId(@Param("id") Long id);
    Tache findById(long id);
    @Query("SELECT t FROM Tache t JOIN t.userList u WHERE u.id = :id AND t.statut = 'non traité'")
    List<Tache> findByUserId(@Param("id") Long id);

    @Query("SELECT t FROM Tache t JOIN t.userList u WHERE u.id = :id AND t.statut = 'traité'")
    List<Tache> findByUserIdtraite(@Param("id") Long id);

}