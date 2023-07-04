package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.UserConverter;
import Workflow.example.Workflow.DTO.UserDto;
import Workflow.example.Workflow.Entity.Role;
import Workflow.example.Workflow.Entity.TacheAtraiter;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/User")
@Tag(name = "User", description = "CRUD User")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @GetMapping("/allUser/")
    @Operation(
            summary = "Find all users",
            description = "Find all users.",
            tags = { "User" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<UserDto> findAll() {
        return userConverter.entityToDto(userService.getAllUsers());
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update user",
            description = "Update a user by id.",
            tags = { "User" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
        return  userService.updateUser(id,user);
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<Set<String>> getRoleNamesByUserId(@PathVariable Long userId) {
        Set<String> roleNames = userService.getRoleNamesByUserId(userId);
        if (roleNames == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(roleNames, HttpStatus.OK);
        }
    }

    @GetMapping("/groups/{groupId}")
    @Operation(
            summary = "getUsersByGroupId ",
            description = "getUsersByGroupId.",
            tags = { "User" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<UserDto> getUsersByGroupId(@PathVariable Long groupId) {
        return userConverter.entityToDto(userService.getUsersByGroupId(groupId));
    }

    @GetMapping("/usersByRole")
    @Operation(
            summary = "usersByRole ",
            description = "usersByRole.",
            tags = { "User" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    public List<UserDto> getUsersByRole() {
        return userConverter.entityToDto(userService.getUsersByRoleUser());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        try {
            return userService.findUserById(id);
        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", ex);
        }
    }

}