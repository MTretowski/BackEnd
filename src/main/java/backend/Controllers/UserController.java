package backend.Controllers;

import backend.DTOs.UpdatePasswordFormDTO;
import backend.DTOs.ResetPasswordFormDTO;
import backend.DTOs.UserDTO;
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


    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/user/add")
    public ResponseEntity<List<UserDTO>> addUser(@RequestBody User user){
        HttpStatus responseStatus = userService.addUser(user);
        return new ResponseEntity<>(userService.findAll(), responseStatus);
    }


    @PutMapping(value = "/user/update")
    public ResponseEntity<List<UserDTO>> updateUser(@RequestBody User user){
        HttpStatus responseStatus = userService.updateUser(user);
        return new ResponseEntity<>(userService.findAll(), responseStatus);
    }


    @PostMapping(value = "/user/resetPassword")
    public ResponseEntity<List<UserDTO>> resetPassword(@RequestBody ResetPasswordFormDTO resetPasswordFormDTO){
        HttpStatus responseStatus = userService.resetPassword(resetPasswordFormDTO);
        return new ResponseEntity<>(userService.findAll(), responseStatus);
    }


    @PostMapping(value = "/user/updatePassword")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordFormDTO updatePasswordFormDTO){
        return new ResponseEntity<>(userService.updatePassword(updatePasswordFormDTO));
    }
}
