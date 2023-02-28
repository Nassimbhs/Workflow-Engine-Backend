package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Role;
import Workflow.example.Workflow.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
