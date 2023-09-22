package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workflow.example.workflow.entity.Role;
import workflow.example.workflow.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }

}
