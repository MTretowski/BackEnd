package backend.Repositories;

import backend.Entities.Fuelling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FuellingRepository extends CrudRepository<Fuelling, Long> {

    List<Fuelling> findAll();

    Fuelling findByDateAndVehicleIdAndAmount (Timestamp date, long id, Double amount);

    Fuelling findById(long id);

    void deleteById(long id);
}
