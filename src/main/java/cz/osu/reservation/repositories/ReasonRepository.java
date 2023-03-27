package cz.osu.reservation.repositories;

import cz.osu.reservation.entities.ReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReasonRepository extends JpaRepository<ReasonEntity, Integer> {
}
