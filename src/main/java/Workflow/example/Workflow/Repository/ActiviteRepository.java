package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite,Long> {
    @Query("SELECT a FROM Activite a WHERE a.workflowActivite.id = :id")
    List<Activite> findByWorkflowId(@Param("id") Long id);

}