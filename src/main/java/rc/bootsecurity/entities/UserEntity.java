package rc.bootsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  // private int active;

  private String roles;

  @OneToMany(mappedBy = "user")
  private Set<RoleEntity> rolesSet;

  public UserEntity(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
