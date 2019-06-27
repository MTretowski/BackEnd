package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.ResetPasswordFormDTO;
import backend.DTOs.UpdatePasswordFormDTO;
import backend.DTOs.UserDTO;
import backend.Entities.User;
import backend.Repositories.UserRepository;
import backend.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.crypto.bcrypt.BCrypt.checkpw;
import static org.springframework.security.crypto.bcrypt.BCrypt.hashpw;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserDTO> findAll() {

        List<User> usersInDatabase = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>(usersInDatabase.size());

        for (User user : usersInDatabase) {
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.isActive(),
                    user.getUserRoleId()
            ));
        }
        return userDTOS;
    }

    @Override
    public ErrorMessageDTO addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return new ErrorMessageDTO("W bazie danych znaleziono użytkownika o takim samym loginie.");
        } else {
            user.setPassword(hashpw(user.getPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
            userRepository.save(user);
            return null;
        }
    }

    @Override
    public ErrorMessageDTO updateUser(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getId()) == null) {
            return new ErrorMessageDTO("Nie odnaleziono użytkownika podanym identyfikatorze - prawdopodobnie został usunięty. Odśwież aplikację i spróbuj ponownie.");
        } else {
            User userTemp = userRepository.findByUsername(userDTO.getUsername());
            if (userTemp == null || userTemp.getId() == userDTO.getId()) {
                User user = new User();
                user.setId(userDTO.getId());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                user.setUsername(userDTO.getUsername());
                user.setPassword(userRepository.findById(userDTO.getId()).getPassword());
                user.setActive(userDTO.isActive());
                user.setUserRoleId(userDTO.getUserRoleId());
                userRepository.save(user);
                return null;
            } else {
                return new ErrorMessageDTO("W bazie danych znaleziono użytkownika o takim samym loginie.");
            }
        }
    }

    @Override
    public ErrorMessageDTO updatePassword(UpdatePasswordFormDTO updatePasswordFormDTO) {
        User user = userRepository.findById(updatePasswordFormDTO.getUserId());
        if (user == null) {
            return new ErrorMessageDTO("Nie odnaleziono użytkownika podanym identyfikatorze - prawdopodobnie został usunięty. Odśwież aplikację i spróbuj ponownie.");
        } else {
            if (!checkpw(updatePasswordFormDTO.getOldPassword(), user.getPassword())) {
                return new ErrorMessageDTO("Podane stare hasło jest błędne.");
            } else {
                user.setPassword(hashpw(updatePasswordFormDTO.getNewPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
                userRepository.save(user);
                return null;
            }
        }
    }

    @Override
    public ErrorMessageDTO resetPassword(ResetPasswordFormDTO resetPasswordFormDTO) {
        User user = userRepository.findById(resetPasswordFormDTO.getUserId());
        if (user == null) {
            return new ErrorMessageDTO("Nie odnaleziono użytkownika podanym identyfikatorze - prawdopodobnie został usunięty. Odśwież aplikację i spróbuj ponownie.");
        } else {
            user.setPassword(hashpw(resetPasswordFormDTO.getNewPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
            userRepository.save(user);
            return null;
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.isActive(),
                user.getUserRoleId()
        );
        return userDTO;
    }
}
