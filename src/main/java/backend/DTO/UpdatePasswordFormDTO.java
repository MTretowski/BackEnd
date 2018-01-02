package backend.DTO;

import lombok.Getter;

@Getter
public class UpdatePasswordFormDTO {
    private long userId;
    private String oldPassword;
    private String newPassword;
}
