package rc.bootsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rc.bootsecurity.dtos.RoleType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roles_seq")
    @SequenceGenerator(
            name = "roles_seq",
            sequenceName = "roles_role_id_seq", allocationSize = 1)
    private long roleId;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String description;

    public RoleEntity(RoleType role) {
        this.role = role;
    }

    /*public RoleEntity(String toString) {
        this.role = toString;
    }*/

  /*private String permission;

  @ManyToMany
  private Set<UserEntity> user;*/

}
