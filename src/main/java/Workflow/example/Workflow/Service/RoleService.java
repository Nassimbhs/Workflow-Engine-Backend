package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Role;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }

}
