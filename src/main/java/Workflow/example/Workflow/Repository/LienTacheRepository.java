package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.LienTache;

import java.util.List;

@Repository
public interface LienTacheRepository extends JpaRepository<LienTache,Long> {
    @Query("SELECT la FROM LienTache la JOIN la.tacheLien a WHERE a.id = :tacheId")
    List<LienTache> findByTacheIdWithTacheLiee(@Param("tacheId") Long tacheId);



}
