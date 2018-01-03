package backend.DTOs;

import lombok.Getter;

@Getter
public class ResetPasswordFormDTO {
    private long userId;
    private String newPassword;
}
