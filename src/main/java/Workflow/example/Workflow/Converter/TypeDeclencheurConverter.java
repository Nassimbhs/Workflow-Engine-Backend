package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.TypeDeclencheurDto;
import Workflow.example.Workflow.Entity.TypeDeclencheur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeDeclencheurConverter {

    @Autowired
    private DonneDeclencheurConverter donneDeclencheurConverter;

    public TypeDeclencheurDto entityToDto(TypeDeclencheur typeDeclencheur)
    {
        TypeDeclencheurDto dto=new TypeDeclencheurDto();
        dto.setId(typeDeclencheur.getId());
        dto.setType(typeDeclencheur.getType());
        dto.setDonneeDeclencheurDtoList(donneDeclencheurConverter.entityToDto(typeDeclencheur.getDonneeDeclencheurs()));
        return dto;
    }

    public List<TypeDeclencheurDto> entityToDto(List<TypeDeclencheur> typeDeclencheurs)
    {
        return typeDeclencheurs.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
