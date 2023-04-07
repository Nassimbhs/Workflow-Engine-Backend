package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
