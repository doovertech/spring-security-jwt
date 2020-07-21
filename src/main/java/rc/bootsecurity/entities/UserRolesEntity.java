package rc.bootsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRolesEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_roles_seq")
    @SequenceGenerator(
            name = "user_roles_seq",
            sequenceName = "user_roles_user_role_id_seq", allocationSize = 1)
    private long userRoleId;
    private long userId;
    private long roleId;

    public UserRolesEntity(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
