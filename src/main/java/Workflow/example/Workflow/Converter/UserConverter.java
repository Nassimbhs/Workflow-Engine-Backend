package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.UserDto;
import Workflow.example.Workflow.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
    public List<UserDto> entityToDto(List<User> users)
    {
        return users.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
