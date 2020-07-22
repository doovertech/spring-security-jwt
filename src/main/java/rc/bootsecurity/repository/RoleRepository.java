package rc.bootsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rc.bootsecurity.dtos.RoleType;
import rc.bootsecurity.entities.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByRole(RoleType roleType);
}
