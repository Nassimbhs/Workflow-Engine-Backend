package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.JsonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JsonDataRepository extends JpaRepository<JsonData,Long> {

    @Query("SELECT jd FROM JsonData jd JOIN jd.tachesAtraiter t WHERE t.id = :tacheAtraiterId")
    List<JsonData> findByTacheAtraiterId(Long tacheAtraiterId);

}
