package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
import backend.Entities.Driver;
import backend.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping(value = "/drivers")
    public ResponseEntity getDrivers() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/driver/add")
    public ResponseEntity addDriver(@RequestBody Driver driver) {
        ErrorMessageDTO message = driverService.addDriver(driver);
        if (message == null) {
            return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

    }


    @PutMapping(value = "/driver/update")
    public ResponseEntity updateDriver(@RequestBody Driver driver) {
        ErrorMessageDTO message = driverService.updateDriver(driver);
        if (message == null) {
            return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
