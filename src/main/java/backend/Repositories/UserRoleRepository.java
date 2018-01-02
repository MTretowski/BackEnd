package backend.Repositories;

import backend.Entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository <UserRole, Long> {

    UserRole findById(long id);

}
