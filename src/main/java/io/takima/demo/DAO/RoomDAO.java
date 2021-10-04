package io.takima.demo.DAO;

import io.takima.demo.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface RoomDAO extends CrudRepository<Room, Long> {

}
