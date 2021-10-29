package io.takima.demo.DAO;

import io.takima.demo.model.Reservation;
<<<<<<< HEAD
import io.takima.demo.model.Room;
=======
import org.jetbrains.annotations.NotNull;
>>>>>>> AjoutCSS#3
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 *
 */
@Repository
public interface ReservationDAO extends CrudRepository<Reservation, Long> {
<<<<<<< HEAD
    List<Reservation> findByRoomIs(BigInteger room);

=======
List<Reservation> findByRoom(BigInteger room);
>>>>>>> AjoutCSS#3
}
