package Workflow.example.Workflow.Repository;

import Workflow.example.Workflow.Entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowRepository extends JpaRepository<Workflow,Long> {

}