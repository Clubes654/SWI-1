package cz.osu.reservation.controlers;

import cz.osu.reservation.entities.PersonEntity;
import cz.osu.reservation.entities.ReasonEntity;
import cz.osu.reservation.entities.VisitEntity;
import cz.osu.reservation.models.Visit;
import cz.osu.reservation.repositories.VisitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ViewAllResult {

    final VisitRepository repositoryVisits;

    private List<Visit> allVisits;

    public ViewAllResult(VisitRepository repositoryVisits) {
        this.repositoryVisits = repositoryVisits;
    }

    public List<Visit> getAllVisitsModels() {

        allVisits = new ArrayList<>();

        try {
            List<VisitEntity> visitEntities = repositoryVisits.findAll();

            for (VisitEntity visitEntity : visitEntities) {
                Integer id = visitEntity.getIdVisit();

                Optional<VisitEntity> tmpVisit = repositoryVisits.findById(id);
                PersonEntity personEntity = tmpVisit.get().getPersonByPersonIdPerson();
                ReasonEntity reasonEntity = tmpVisit.get().getReasonByReasonCodeReason();

                Visit visitModel = new Visit();
                visitModel.setId(tmpVisit.get().getIdVisit());
                visitModel.setDay(tmpVisit.get().getDay());
                visitModel.setMonth(tmpVisit.get().getMonth());
                visitModel.setYear(tmpVisit.get().getYear());
                visitModel.setInsurance_code(personEntity.getInsuranceCode());
                visitModel.setHour(tmpVisit.get().getHour());
                visitModel.setName(personEntity.getName());
                visitModel.setLastName(personEntity.getLastName());
                visitModel.setEmail(personEntity.getEmail());
                visitModel.setPhone(personEntity.getPhone());
                visitModel.setDescription(reasonEntity.getName());
                visitModel.setDescriptionReason(reasonEntity.getDescription());
                allVisits.add(visitModel);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allVisits;


    }


}
