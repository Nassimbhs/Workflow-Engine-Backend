package workflow.example.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import workflow.example.workflow.entity.GroupeUser;
import workflow.example.workflow.entity.User;
import workflow.example.workflow.repository.GroupeUserRepository;
import workflow.example.workflow.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("GroupeUser", groupeUser);
        responseBody.put("message", "group successfully created!");

        return ResponseEntity.ok().body(responseBody);

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
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("GroupeUser", groupeUser);
        responseBody.put("message", "group successfully updated !");

        return ResponseEntity.ok().body(responseBody);
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
