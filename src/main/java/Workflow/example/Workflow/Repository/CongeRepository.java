package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {
    Conge findByTacheCongeIdAndTacheCongeUserListId(Long tacheId, Long userId);

}