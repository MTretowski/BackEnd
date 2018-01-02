package backend.DTO;

import lombok.Getter;

@Getter
public class ResetPasswordFormDTO {
    private long userId;
    private String newPassword;
}
