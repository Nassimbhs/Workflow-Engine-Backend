package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.DonneeDeclencheur;
import Workflow.example.Workflow.Entity.Workflow;
import Workflow.example.Workflow.Repository.DonneeDeclencheurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DonneeDeclencheurService {

    @Autowired
    private DonneeDeclencheurRepository donneeDeclencheurRepository;

    @Transactional
    public ResponseEntity<Object> addDonneeDeclencheur(DonneeDeclencheur donneeDeclencheur) {
        donneeDeclencheurRepository
                .findById(donneeDeclencheur.getId())
                .ifPresentOrElse(
                        x -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DonneeDeclencheur id already exit !");
                        },
                        () -> {
                            donneeDeclencheurRepository.save(donneeDeclencheur);
                        });

        return ResponseEntity.accepted().body("DonneeDeclencheur successfully Created !");
    }

    @Transactional
    public ResponseEntity<Object> updateDonneeDeclencheur(Long id, DonneeDeclencheur donneeDeclencheur) {
        donneeDeclencheurRepository.findById(id).ifPresentOrElse(
                d -> {
                    d.setUrlWebhook(donneeDeclencheur.getUrlWebhook());
                    d.setTokenWenhook(donneeDeclencheur.getTokenWenhook());
                    d.setLocalhostDB(donneeDeclencheur.getLocalhostDB());
                    d.setUsernameDB(donneeDeclencheur.getUsernameDB());
                    d.setPasswordDB(donneeDeclencheur.getPasswordDB());

                    donneeDeclencheurRepository.save(d);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DonneeDeclencheur not found !");
                });
        return ResponseEntity.accepted().body("DonneeDeclencheur successfully updated !");
    }

    @Transactional
    public void deleteDonneeDeclencheurById(Long id) {
        DonneeDeclencheur donneeDeclencheur = donneeDeclencheurRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "DonneeDeclencheur not found !"));
        donneeDeclencheurRepository.delete(donneeDeclencheur);
    }

    public List<DonneeDeclencheur> getAllDonneeDeclencheur() {
        return (List<DonneeDeclencheur>) donneeDeclencheurRepository.findAll();
    }

    public DonneeDeclencheur findDonneeDeclencheurById(Long id) {
        Optional<DonneeDeclencheur> donneeDeclencheurOptional = donneeDeclencheurRepository.findById(id);
        if (donneeDeclencheurOptional.isPresent()) {
            return donneeDeclencheurOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "donneeDeclencheur not found !");
        }
    }

}