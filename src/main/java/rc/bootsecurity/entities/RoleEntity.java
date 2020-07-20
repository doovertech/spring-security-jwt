package rc.bootsecurity.entities;

import lombok.Data;
import rc.bootsecurity.dtos.RoleType;
import rc.bootsecurity.dtos.User;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userRoleId;

  private RoleType role;

  private String description;

  private String permission;

  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

}
