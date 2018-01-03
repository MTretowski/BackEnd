package backend.Repositories;

import backend.Entities.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    Trip findByEndingMeasurmentId(long id);

    Trip findByStartingMeasurmentId(long id);

    List<Trip> findAll();

    List<Trip> findAllByVehicleId(long id);

    Trip findById(long id);

    void deleteById(long id);
}
