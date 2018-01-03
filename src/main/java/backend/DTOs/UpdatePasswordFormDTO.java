package backend.DTOs;

import lombok.Getter;

@Getter
public class UpdatePasswordFormDTO {
    private long userId;
    private String oldPassword;
    private String newPassword;
}
