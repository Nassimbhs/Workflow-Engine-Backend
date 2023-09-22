package workflow.example.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workflow.example.workflow.entity.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow,Long> {

}