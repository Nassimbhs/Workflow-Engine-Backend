package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Conge;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

}