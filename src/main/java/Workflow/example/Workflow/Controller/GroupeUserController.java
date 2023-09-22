package workflow.example.workflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workflow.example.workflow.converter.GroupeUserConverter;
import workflow.example.workflow.dto.GroupeUserDto;
import workflow.example.workflow.entity.GroupeUser;
import workflow.example.workflow.service.GroupeUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/GroupeUser")
@Tag(name = "GroupeUser", description = "CRUD GroupeUser")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeUserController {

    @Autowired
    private GroupeUserService groupeUserService;
    @Autowired
    private GroupeUserConverter groupeUserConverter;

    @PostMapping("/addGroupeUser")
    @Operation(
            summary = "add GroupeUser",
            description = "create GroupeUser.",
            tags = {"GroupeUser"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GroupeUser.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> addTache(@RequestBody GroupeUser groupeUser) {
        return groupeUserService.addGroupeUser(groupeUser);
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update GroupeUser",
            description = "Update GroupeUser by id.",
            tags = {"GroupeUser"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GroupeUser.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateTache(@PathVariable Long id, @RequestBody GroupeUser groupeUser) {
        return groupeUserService.updateGroupeUser(id, groupeUser);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete GroupeUser",
            description = "Delete GroupeUser by id.",
            tags = {"GroupeUser"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GroupeUser.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public void deleteTache(@PathVariable Long id) {
        groupeUserService.deleteGroupeUserById(id);
    }

    @GetMapping("/allgroups/")
    @Operation(
            summary = "Find all GroupeUser",
            description = "Find all GroupeUser.",
            tags = {"GroupeUser"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GroupeUserDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<GroupeUserDto> findAll() {
        return groupeUserConverter.entityToDto(groupeUserService.getAllgroups());
    }

    @GetMapping("/getGroup/{id}")
    @Operation(
            summary = "Find group",
            description = "Find group by id.",
            tags = {"GroupeUser"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = GroupeUserDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public GroupeUserDto findTacheById(@PathVariable Long id) {
        return groupeUserConverter.entityToDto(groupeUserService.findGroupById(id));
    }

    @PostMapping("/groups/{groupId}/users")
    public ResponseEntity<Void> addUsersToGroup(@PathVariable Long groupId, @RequestBody List<Long> userIds) {
        groupeUserService.addUsersToGroup(groupId, userIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        groupeUserService.removeUserFromGroup(groupId, userId);
        return ResponseEntity.ok().build();
    }

}
