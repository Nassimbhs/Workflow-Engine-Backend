package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.GroupeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeUserRepository extends JpaRepository<GroupeUser,Long> {
}
