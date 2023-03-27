package cz.osu.reservation.repositories;


import cz.osu.reservation.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

}