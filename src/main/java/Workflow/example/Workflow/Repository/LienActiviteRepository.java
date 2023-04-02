package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.LienActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LienActiviteRepository extends JpaRepository<LienActivite,Long> {
    @Query("SELECT la FROM LienActivite la JOIN la.activiteLien a WHERE a.id = :activiteId")
    List<LienActivite> findByActiviteIdWithActiviteLiee(@Param("activiteId") Long activiteId);


}
