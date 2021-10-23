package io.takima.demo.DAO;

import io.takima.demo.model.Jointure;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 *
 */
@Repository
public interface JointureDAO extends CrudRepository<Jointure, Long> {
//    @Modifying
//    @Query("SELECT * FROM jointures JOIN reservations ON reservation=reservation_id WHERE reservation= :id_resa")
//    Integer GetMembresReu(@Param("id_resa") BigInteger id_resa);

}
