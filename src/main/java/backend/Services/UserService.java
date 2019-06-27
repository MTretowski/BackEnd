package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.ResetPasswordFormDTO;
import backend.DTOs.UpdatePasswordFormDTO;
import backend.DTOs.UserDTO;
import backend.Entities.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    ErrorMessageDTO addUser(User user);

    ErrorMessageDTO updateUser(UserDTO userDTO);

    ErrorMessageDTO updatePassword(UpdatePasswordFormDTO updatePasswordFormDTO);

    ErrorMessageDTO resetPassword(ResetPasswordFormDTO resetPasswordFormDTO);

    UserDTO findByUsername(String username);
}
