package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.Langue;

@Repository
public interface LangueRepository extends JpaRepository<Langue,Long> {
}
