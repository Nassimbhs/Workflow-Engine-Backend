package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv,Long> {

}
