package backend.Repositories;

import backend.Entities.Measurment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MeasurmentRepository extends CrudRepository<Measurment, Long> {

    List<Measurment> findAll();

    void deleteById(long id);

    Measurment findByVehicleIdAndDate(long id, Timestamp date);

    Measurment findById(long id);
}
