package rc.bootsecurity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long userId;

    private String email;

    private String username;

    private String password;

    private boolean active = true;

    private List<RoleType> roles;

    // private String permissions;

    /*public List<RoleType> getRoleList() {
        if (this.roles.size() > 0) {
            return roles;
        }
        return new ArrayList<>();
    }*/

    /*public List<String> getPermissionList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }*/
}
