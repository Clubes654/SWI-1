package cz.osu.reservation.controlers;

import cz.osu.reservation.repositories.PersonRepository;
import cz.osu.reservation.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PersonController {

    @Autowired
    PersonRepository repository;

    @GetMapping("/persons")
    List<PersonEntity> findAll() {
        return repository.findAll();
    }

    @GetMapping("/persons/{id}")
    Optional<PersonEntity> findPerson(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PutMapping("/persons/{id}")
    ResponseEntity<String> createOrUpdate(@Valid @RequestBody PersonEntity newPerson, @PathVariable Integer id) {
        PersonEntity person = repository.findById(id)
                .map(x -> {
                    x.setName(newPerson.getName());
                    x.setLastName(newPerson.getLastName());
                    x.setPhone(newPerson.getPhone());
                    x.setInsuranceCode(newPerson.getInsuranceCode());
                    x.setEmail(newPerson.getEmail());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    return repository.save(newPerson);
                });

        return ResponseEntity.ok("Person is valid");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Map> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Map> ret = new HashMap<>();
        ret.put("errors", errors);
        return ret;
    }
}
