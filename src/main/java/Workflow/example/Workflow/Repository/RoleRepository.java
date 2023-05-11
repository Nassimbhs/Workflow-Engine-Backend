package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.ERole;
import Workflow.example.Workflow.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERole name);

}
