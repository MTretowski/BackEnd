package backend.Services;

import backend.DTOs.ResetPasswordFormDTO;
import backend.DTOs.UpdatePasswordFormDTO;
import backend.DTOs.UserDTO;
import backend.Entities.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    HttpStatus addUser(User user);

    HttpStatus updateUser(User user);

    void deleteUser(long id);

    HttpStatus updatePassword(UpdatePasswordFormDTO updatePasswordFormDTO);

    HttpStatus resetPassword(ResetPasswordFormDTO resetPasswordFormDTO);
}
