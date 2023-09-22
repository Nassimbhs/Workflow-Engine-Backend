package workflow.example.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import workflow.example.workflow.entity.Role;
import workflow.example.workflow.service.RoleService;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createNewRole")
    public Role createNewRole(Role role){
        return roleService.createNewRole(role);
    }

}
