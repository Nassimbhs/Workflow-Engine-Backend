package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.TypeDeclencheur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDeclencheurRepository extends JpaRepository<TypeDeclencheur,Long> {
}
