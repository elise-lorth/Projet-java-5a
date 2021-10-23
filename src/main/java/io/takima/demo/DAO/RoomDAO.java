package io.takima.demo.DAO;

import io.takima.demo.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface RoomDAO extends CrudRepository<Room, Long> {
List<Room> findByCapacity(int capacity);
List<Room> findByCapacityGreaterThanEqual(int capacity);
List<Room> findByBoardGreaterThanEqual(int board);
List<Room> findByScreenGreaterThanEqual(int screen);
List<Room> findByTabletGreaterThanEqual(int tablet);

}
