package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DriverDTO {
    private long id;
    private String firstName;
    private String lastName;
    private boolean active;
}
