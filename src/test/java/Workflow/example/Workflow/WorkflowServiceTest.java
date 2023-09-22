package workflow.example.workflow;

import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import workflow.example.workflow.entity.Tache;
import workflow.example.workflow.entity.Workflow;
import workflow.example.workflow.repository.TacheRepository;
import workflow.example.workflow.repository.WorkflowRepository;
import workflow.example.workflow.service.TableService;
import workflow.example.workflow.service.WorkflowService;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WorkflowServiceTest {

    @InjectMocks
    private WorkflowService workflowService;

    @Mock
    private WorkflowRepository workflowRepository;

    @Mock
    private TacheRepository tacheRepository;

    @Mock
    private TableService tableService;

    @Test
    public void testAddWorkflow() {
        Workflow workflow = new Workflow();
        workflow.setName("Sample Workflow");

        // Stub the necessary behavior for this test
        when(workflowRepository.save(any(Workflow.class))).thenReturn(workflow);

        ResponseEntity<Object> responseEntity = workflowService.addWorkflow(workflow);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(Objects.requireNonNull(responseEntity.getBody()));
        Object responseBody = responseEntity.getBody();
        assertTrue(responseBody instanceof Map);
        Map<String, Object> responseMap = (Map<String, Object>) responseBody;
        assertEquals("Workflow successfully created!", responseMap.get("message"));
        assertEquals(workflow, responseMap.get("workflow"));

        verify(tacheRepository, times(2)).save(any(Tache.class));
    }

    @Test
    public void testUpdateWorkflow() {
        Long workflowId = 1L;
        Workflow existingWorkflow = new Workflow();
        existingWorkflow.setId(workflowId);
        existingWorkflow.setName("Existing Workflow");

        Workflow updatedWorkflow = new Workflow();
        updatedWorkflow.setId(workflowId);
        updatedWorkflow.setName("Updated Workflow");

        // Mock the behavior of workflowRepository
        when(workflowRepository.findById(eq(workflowId))).thenReturn(Optional.of(existingWorkflow));
        when(workflowRepository.save(any(Workflow.class))).thenReturn(updatedWorkflow);

        ResponseEntity<Object> responseEntity = workflowService.updateWorkflow(workflowId, updatedWorkflow);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Object responseBody = responseEntity.getBody(); // Get the response body as an Object
        assertTrue(responseBody instanceof Map);
        Map<String, Object> responseMap = (Map<String, Object>) responseBody;
        assertEquals("Workflow successfully updated!", responseMap.get("message"));
        assertEquals(updatedWorkflow, responseMap.get("workflow"));

    }

    @Test
    public void testDeleteWorkflowById() {
        Long workflowId = 1L;
        Workflow existingWorkflow = new Workflow();
        existingWorkflow.setId(workflowId);

        when(workflowRepository.findById(eq(workflowId))).thenReturn(Optional.of(existingWorkflow));

        workflowService.deleteWorkflowById(workflowId);

        verify(workflowRepository).delete(existingWorkflow);
    }

    @Test
    public void testGetAllWorkflows() {
        List<Workflow> workflows = Arrays.asList(new Workflow(), new Workflow());

        when(workflowRepository.findAll()).thenReturn(workflows);

        List<Workflow> result = workflowService.getAllWorkflows();

        assertEquals(workflows, result);
    }

    @Test
    public void testFindWorkflowById() {
        Long workflowId = 1L;
        Workflow existingWorkflow = new Workflow();
        existingWorkflow.setId(workflowId);

        when(workflowRepository.findById(eq(workflowId))).thenReturn(Optional.of(existingWorkflow));

        Workflow result = workflowService.findWorkflowById(workflowId);
        assertEquals(existingWorkflow, result);
    }

    @Test
    public void testGetWorkflowTables() throws NotFoundException {
        Long workflowId = 1L;
        Workflow workflow = new Workflow();
        workflow.setId(workflowId);
        workflow.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        workflow.setUsername("user");
        workflow.setPassword("password");
        workflow.setSgbd("MySQL");

        when(workflowRepository.findById(eq(workflowId))).thenReturn(Optional.of(workflow));

        List<String> tables = Arrays.asList("table1", "table2");
        when(tableService.getTables(eq(workflow.getJdbcUrl()), eq(workflow.getUsername()), eq(workflow.getPassword()), eq(workflow.getSgbd()))).thenReturn(tables);

        List<String> result = workflowService.getWorkflowTables(workflowId);
        assertEquals(tables, result);
    }
}
