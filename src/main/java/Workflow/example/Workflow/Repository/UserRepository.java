package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workflow.example.workflow.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.groups g WHERE g.id = :groupId")
    List<User> findByGroupId(@Param("groupId") Long groupId);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_USER'")
    List<User> findUsersByRoleUser();
    @Query("SELECT u FROM User u WHERE u.id IN :userIds")
    List<User> findAllByIdIn(@Param("userIds") List<Long> userIds);

}
