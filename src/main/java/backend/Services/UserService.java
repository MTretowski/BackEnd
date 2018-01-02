package backend.Services;

import backend.DTO.ResetPasswordFormDTO;
import backend.DTO.UpdatePasswordFormDTO;
import backend.DTO.UserDTO;
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
