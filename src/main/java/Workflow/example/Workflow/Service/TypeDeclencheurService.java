package Workflow.example.Workflow.Service;

import Workflow.example.Workflow.Entity.Tache;
import Workflow.example.Workflow.Entity.TypeDeclencheur;
import Workflow.example.Workflow.Repository.TypeDeclencheurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TypeDeclencheurService {
    @Autowired
    TypeDeclencheurRepository typeDeclencheurRepository;

    @Transactional
    public ResponseEntity<Object> addTypeDeclencheur(TypeDeclencheur typeDeclencheur) {
        typeDeclencheurRepository
                .findById(typeDeclencheur.getId())
                .ifPresentOrElse(
                        x -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TypeDeclencheur id already exit !");
                        },
                        () -> {
                            typeDeclencheurRepository.save(typeDeclencheur);
                        });

        return ResponseEntity.accepted().body("TypeDeclencheur successfully Created !");
    }

    @Transactional
    public ResponseEntity<Object> updateDeclencheur(Long id, TypeDeclencheur typeDeclencheur) {
        typeDeclencheurRepository.findById(id).ifPresentOrElse(
                t -> {
                    t.setType(typeDeclencheur.getType());
                    typeDeclencheurRepository.save(t);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TypeDeclencheur not found !");
                });
        return ResponseEntity.accepted().body("TypeDeclencheur successfully updated !");
    }

    @Transactional
    public void deleteTypeDeclencheurById(Long id) {
        TypeDeclencheur typeDeclencheur = typeDeclencheurRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TypeDeclencheur not found !"));
        typeDeclencheurRepository.delete(typeDeclencheur);
    }

    public List<TypeDeclencheur> getAllTypeDeclencheur() {
        return (List<TypeDeclencheur>) typeDeclencheurRepository.findAll();
    }

    public TypeDeclencheur findTypeDeclencheurById(Long id) {
        Optional<TypeDeclencheur> typeDeclencheurOptional = typeDeclencheurRepository.findById(id);
        if (typeDeclencheurOptional.isPresent()) {
            return typeDeclencheurOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TypeDeclencheur not found !");
        }
    }
}
