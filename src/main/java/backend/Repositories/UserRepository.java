package backend.Repositories;

import backend.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndActive(String username, boolean active);

    List<User> findAll();

    User findById(long id);
}
