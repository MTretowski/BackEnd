package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
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

@Controller
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/get/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping(value = "/user/add")
    public ResponseEntity addUser(@RequestBody User user) {
        ErrorMessageDTO message = userService.addUser(user);
        if (message == null) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/user/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
        ErrorMessageDTO message = userService.updateUser(userDTO);
        if (message == null) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/user/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordFormDTO resetPasswordFormDTO) {
        ErrorMessageDTO message = userService.resetPassword(resetPasswordFormDTO);
        if (message == null) {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/user/updatePassword")
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordFormDTO updatePasswordFormDTO) {
        ErrorMessageDTO message = userService.updatePassword(updatePasswordFormDTO);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
