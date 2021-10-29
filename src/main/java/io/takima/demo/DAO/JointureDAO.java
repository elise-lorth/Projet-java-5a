package io.takima.demo.DAO;

import io.takima.demo.model.Jointure;
import io.takima.demo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 *
 */
@Repository
public interface JointureDAO extends CrudRepository<Jointure, Long> {
    List<Jointure> findByReservation(long reservation);


}
