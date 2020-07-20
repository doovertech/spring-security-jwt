package rc.bootsecurity.dtos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {

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