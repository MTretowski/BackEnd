package backend.Repositories;

import backend.Entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Vehicle findById(long id);

    Vehicle findByPlateNumbers(String plateNumbers);

    List<Vehicle> findAll();
}
