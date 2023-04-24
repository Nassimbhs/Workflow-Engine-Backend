package Workflow.example.Workflow.Controller;

import Workflow.example.Workflow.Converter.UserConverter;
import Workflow.example.Workflow.DTO.UserDto;
import Workflow.example.Workflow.DTO.WorkflowDto;
import Workflow.example.Workflow.Entity.User;
import Workflow.example.Workflow.Entity.Workflow;
import Workflow.example.Workflow.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUser/{id}")
    @Operation(
            summary = "Find user",
            description = "Find user by id.",
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
    public UserDto findUserById(@PathVariable Long id) {
        return userConverter.entityToDto(userService.findUserById(id));
    }

}