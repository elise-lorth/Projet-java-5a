package io.takima.demo.DAO;

import io.takima.demo.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface ReservationDAO extends CrudRepository<Reservation, Long> {

}