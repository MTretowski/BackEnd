package backend.Services;

import backend.DTO.ResetPasswordFormDTO;
import backend.DTO.UpdatePasswordFormDTO;
import backend.DTO.UserDTO;
import backend.Entities.User;
import backend.Repositories.UserRepository;
import backend.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public List<UserDTO> findAll() {

        List<User> usersInDatabase = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : usersInDatabase) {
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.isActive(),
                    user.getUserRoleId(),
                    user.getUserRoleByUserRoleId().getName()
            ));
        }
        return userDTOS;
    }


    public HttpStatus addUser(User user) {
        user.setUserRoleByUserRoleId(userRoleRepository.findById(user.getUserRoleId()));
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return HttpStatus.BAD_REQUEST;
        } else {
            user.setPassword(hashpw(user.getPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
            userRepository.save(user);
            return HttpStatus.OK;
        }
    }

    public HttpStatus updateUser(User user) {
        user.setUserRoleByUserRoleId(userRoleRepository.findById(user.getUserRoleId()));
        if (userRepository.findById(user.getId()) == null) {
            return HttpStatus.BAD_REQUEST;
        } else {
            userRepository.save(user);
            return HttpStatus.OK;
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public HttpStatus updatePassword(UpdatePasswordFormDTO updatePasswordFormDTO) {
        User user = userRepository.findById(updatePasswordFormDTO.getUserId());
        if (user == null) {
            return HttpStatus.BAD_REQUEST;
        } else {
            if (!checkpw(updatePasswordFormDTO.getOldPassword(), user.getPassword())) {
                return HttpStatus.BAD_REQUEST;
            } else {
                user.setPassword(hashpw(updatePasswordFormDTO.getNewPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
                userRepository.save(user);
                return HttpStatus.OK;
            }
        }
    }

    public HttpStatus resetPassword(ResetPasswordFormDTO resetPasswordFormDTO) {
        User user = userRepository.findById(resetPasswordFormDTO.getUserId());
        if (user == null) {
            return HttpStatus.BAD_REQUEST;
        } else {
            user.setPassword(hashpw(resetPasswordFormDTO.getNewPassword(), "$2a$10$251BUgwQV7l/3xVGpEIYbu"));
            userRepository.save(user);
            return HttpStatus.OK;
        }
    }
}
