package cz.osu.reservation.controlers;

import cz.osu.reservation.models.Visit;
import cz.osu.reservation.repositories.VisitRepository;
import cz.osu.reservation.entities.VisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VisitController {

    @Autowired
    VisitRepository repository;

    @GetMapping("/visits")
    List<Visit> findAll() {
        // return repository.findAll();

        ViewAllResult viewAllResult = new ViewAllResult(repository);
        return viewAllResult.getAllVisitsModels();
    }

    @GetMapping("/visits/withParamWithHour")
        // vrati vsechny blocked visity v dany den v danou hodinu
    ResponseEntity<VisitEntity> findBlockedVisitsInDayWithHour(@RequestParam int year, @RequestParam int month, @RequestParam int day, @RequestParam String hour) {

        return new ResponseEntity<VisitEntity>(repository.findVisitInTime(year, month, day, java.sql.Time.valueOf(hour)), HttpStatus.OK);
    }

    @GetMapping("/visits/withParamWithoutHour")
        // vrati vsechny blocked visity v dany den
    List<VisitEntity> findBlockedVisitsInDay(@RequestParam int year, @RequestParam int month, @RequestParam int day) {
        return repository.findVisitInDate(year, month, day);
    }

    @PostMapping("visits/create")
    public ResponseEntity<VisitEntity> createNewRecordVisit(@RequestBody VisitCreationRequest newVisit) throws ServerException {
        VisitEntity visit = newVisit.getVisit();
        visit.setPersonByPersonIdPerson(newVisit.getPerson());
        visit.setReasonByReasonCodeReason(newVisit.getReason());

        VisitEntity visitEntityTime = repository.findVisitInTime(visit.getYear(), visit.getMonth(), visit.getDay(), visit.getHour());
        if (visitEntityTime != null) {
            //  throw new ServerException("Rezervace v dany čas již existuje!");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Rezervace v daný čas již existuje!");

        }
        VisitEntity visitRet = null;
        try {
            visitRet = repository.save(visit);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            // throw new ServerException("Nepodařilo se vytvořit návštěvu", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Nepodařilo se vytvořit návštěvu", ex);
        }

        return new ResponseEntity<>(visitRet, HttpStatus.CREATED);


    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("visit/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        try {
            repository.deleteById(Math.toIntExact(id));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nepodařilo se smazat návštevu č. " + id + " pravděpodobně neexistuje");
        }
        return "Delete by id : " + Integer.toString(id) + " ok";
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, Map> handleValidationExceptions(ResponseStatusException ex) {
        Map<String, String> errors = new HashMap<>();
        //  ex.gea

        // ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = "text";
        String errorMessage = ex.getMessage();
        errors.put(fieldName, errorMessage);


        Map<String, Map> ret = new HashMap<>();
        ret.put("errors", errors);
        return ret;
    }


    /*@PutMapping("/persons/{id}")
    ResponseEntity<String> createOrUpdate(@Valid @RequestBody PersonEntity newPerson, @PathVariable Long id) {
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
                });*/
}
