package backend.Repositories;

import backend.Entities.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier findByName(String name);

    Supplier findById(long id);
}
