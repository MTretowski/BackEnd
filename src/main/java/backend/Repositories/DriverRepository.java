package backend.Repositories;

import backend.Entities.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {

    List<Driver> findAll();

    Driver findByFirstNameAndLastName(String firstName, String lastName);

    Driver findById(long id);
}
