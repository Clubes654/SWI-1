package cz.osu.reservation.repositories;

import cz.osu.reservation.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<VisitEntity, Integer> {

    @Query(value = "SELECT * FROM VISIT WHERE year = ?1 and month = ?2 and day=?3 and hour=?4", nativeQuery = true)
    VisitEntity findVisitInTime(int year, int month, int day, java.sql.Time time);

    @Query(value = "SELECT * FROM VISIT WHERE year = ?1 and month = ?2 and day=?3", nativeQuery = true)
    List<VisitEntity> findVisitInDate(int year, int month, int day);
}
