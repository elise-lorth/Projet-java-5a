package io.takima.demo.DAO;

import io.takima.demo.model.Reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 *
 */
@Repository
public interface ReservationDAO extends CrudRepository<Reservation, Long> {

    List<Reservation> findByRoomIs(BigInteger room);

}
