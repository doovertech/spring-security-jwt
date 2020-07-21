package rc.bootsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_seq")
    @SequenceGenerator(
            name = "users_seq",
            sequenceName = "users_user_id_seq", allocationSize = 1)
    private long userId;

    private String email;
    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // private int active;

  /*private String roles;

  @ManyToMany
  private Set<RoleEntity> rolesSet;*/

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
