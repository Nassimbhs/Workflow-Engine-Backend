package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.JsonData;

import java.util.List;

@Repository
public interface JsonDataRepository extends JpaRepository<JsonData,Long> {

    @Query("SELECT jd FROM JsonData jd JOIN jd.tachesAtraiter t WHERE t.id = :tacheAtraiterId")
    List<JsonData> findByTacheAtraiterId(Long tacheAtraiterId);

}
