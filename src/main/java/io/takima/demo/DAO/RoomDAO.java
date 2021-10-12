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
List<Room> findByBoard(int board);
List<Room> findByScreen(int screen);
List<Room> findByTablet(int tablet);
}

