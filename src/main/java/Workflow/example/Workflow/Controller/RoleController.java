package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.DTO.UserDto;
import Workflow.example.Workflow.Entity.Role;
import Workflow.example.Workflow.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createNewRole")
    public Role createNewRole(Role role){
        return roleService.createNewRole(role);
    }

}
