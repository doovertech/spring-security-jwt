package rc.bootsecurity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private long userRoleId;
    private RoleType role;
    private String description;
    private String permission;

/*
  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;
*/

}