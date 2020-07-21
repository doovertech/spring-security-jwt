package rc.bootsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rc.bootsecurity.entities.UserRolesEntity;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRolesEntity, Long> {
}
