package Workflow.example.Workflow.Converter;

import Workflow.example.Workflow.DTO.DonneeDeclencheurDto;
import Workflow.example.Workflow.DTO.ResponsableDto;
import Workflow.example.Workflow.Entity.DonneeDeclencheur;
import Workflow.example.Workflow.Entity.Responsable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DonneDeclencheurConverter {

    public DonneeDeclencheurDto entityToDto(DonneeDeclencheur donneeDeclencheur){
        DonneeDeclencheurDto dto = new DonneeDeclencheurDto();
        dto.setId(donneeDeclencheur.getId());
        dto.setUrlWebhook(donneeDeclencheur.getUrlWebhook());
        dto.setTokenWenhook(donneeDeclencheur.getTokenWenhook());
        dto.setLocalhostDB(donneeDeclencheur.getLocalhostDB());
        dto.setUsernameDB(donneeDeclencheur.getUsernameDB());
        dto.setPasswordDB(donneeDeclencheur.getPasswordDB());
        return dto;
    }
    public List<DonneeDeclencheurDto> entityToDto(List<DonneeDeclencheur> donneeDeclencheurs)
    {
        return donneeDeclencheurs.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
