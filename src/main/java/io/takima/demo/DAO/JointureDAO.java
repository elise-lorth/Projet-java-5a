package io.takima.demo.DAO;

import io.takima.demo.model.Jointure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface JointureDAO extends CrudRepository<Jointure, Long> {

}
