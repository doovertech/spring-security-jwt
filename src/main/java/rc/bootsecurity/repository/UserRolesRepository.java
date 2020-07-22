package rc.bootsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rc.bootsecurity.entities.RoleEntity;
import rc.bootsecurity.entities.UserRolesEntity;

import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRolesEntity, Long> {
    List<UserRolesEntity> findAllByUserId(long userId);

}
