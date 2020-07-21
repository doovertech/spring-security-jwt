package rc.bootsecurity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<RoleType> roles;
}
