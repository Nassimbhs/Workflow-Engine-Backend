package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.GroupeUser;
import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Repository.GroupeUserRepository;
import Workflow.example.Workflow.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeUserService {

    @Autowired
    private GroupeUserRepository groupeUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<Object> addGroupeUser(GroupeUser groupeUser) {
        Long id = groupeUser.getId();
        if (id != null && groupeUserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "group with id " + id + " already exists");
        }
        groupeUserRepository.save(groupeUser);
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("GroupeUser", groupeUser);
                    put("message", "group successfully created!");
                }});
    }

    @Transactional
    public ResponseEntity<Object> updateGroupeUser(Long id, GroupeUser groupeUser) {
        groupeUserRepository.findById(id).ifPresentOrElse(
                a -> {
                    a.setNom(groupeUser.getNom());
                    a.setDescription(groupeUser.getDescription());
                    groupeUserRepository.save(a);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "group not found !");
                });
        return ResponseEntity.ok()
                .body(new HashMap<String, Object>() {{
                    put("GroupeUser", groupeUser);
                    put("message", "Group successfully updated!");
                }});
    }

    @Transactional
    public void deleteGroupeUserById(Long id) {
        GroupeUser groupeUser = groupeUserRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "group not found !"));
        groupeUserRepository.delete(groupeUser);
    }

    public List<GroupeUser> getAllgroups() {
        return groupeUserRepository.findAll();
    }

    public GroupeUser findGroupById(Long id) {
        Optional<GroupeUser> groupeUserOptional = groupeUserRepository.findById(id);
        if (groupeUserOptional.isPresent()) {
            return groupeUserOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
    }
    public void addUsersToGroup(Long groupId, List<Long> userIds) {
        GroupeUser groupUser = groupeUserRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id " + groupId));

        List<User> users = userRepository.findAllById(userIds);
        groupUser.getUsers().addAll(users);

        groupeUserRepository.save(groupUser);
    }

    public void removeUserFromGroup(Long groupId, Long userId) {
        GroupeUser groupUser = groupeUserRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id " + groupId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));

        groupUser.getUsers().remove(user);

        groupeUserRepository.save(groupUser);
    }

}
