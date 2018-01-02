package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private boolean active;
    private long userRoleId;
    private String userRole;

}
