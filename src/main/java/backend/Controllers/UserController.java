package backend.Controllers;

import backend.DTO.UpdatePasswordFormDTO;
import backend.DTO.ResetPasswordFormDTO;
import backend.DTO.UserDTO;
import backend.Entities.User;
import backend.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/user/add")
    public ResponseEntity<Void> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user));
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/user/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user));
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/user/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/user/resetPassword")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordFormDTO resetPasswordFormDTO){
        return new ResponseEntity<>(userService.resetPassword(resetPasswordFormDTO));
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/user/updatePassword")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordFormDTO updatePasswordFormDTO){
        return new ResponseEntity<>(userService.updatePassword(updatePasswordFormDTO));
    }


}
