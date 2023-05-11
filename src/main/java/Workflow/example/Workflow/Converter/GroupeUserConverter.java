package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.GroupeUserDto;
import Workflow.example.Workflow.Entity.GroupeUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupeUserConverter {

    public GroupeUserDto entityToDto(GroupeUser groupeUser){
        GroupeUserDto dto = new GroupeUserDto();
        dto.setId(groupeUser.getId());
        dto.setNom(groupeUser.getNom());
        dto.setDescription(groupeUser.getDescription());
        return dto;
    }
    public List<GroupeUserDto> entityToDto(List<GroupeUser> groupeUsers)
    {
        return groupeUsers.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
