package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.TacheAtraiter;

import java.util.List;

@Repository
public interface TacheAtraiteRepository extends JpaRepository<TacheAtraiter,Long> {
    List<TacheAtraiter> findByTacheAtraiteAndResponsable(Tache tache, Long responsable);
    boolean existsByTacheAtraiteAndResponsable(Tache tache, Long responsable);
    List<TacheAtraiter> findByResponsable(Long responsableId);
    @Query("SELECT t FROM TacheAtraiter t WHERE t.statut = 'traité' AND t.responsable = :responsableId")
    List<TacheAtraiter> getTachesTraiteesParResponsable(@Param("responsableId") Long responsableId);

}
