package backend.Controllers;

import backend.DTOs.DriverDTO;
import backend.Entities.Driver;
import backend.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/drivers")
    public ResponseEntity<List<DriverDTO>> getDrivers() {
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/driver/add")
    public ResponseEntity<List<DriverDTO>> addDriver(@RequestBody Driver driver) {
        HttpStatus responseStatus = driverService.addDriver(driver);
        return new ResponseEntity<>(driverService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/driver/update")
    public ResponseEntity<List<DriverDTO>> updateDriver(@RequestBody Driver driver) {
        HttpStatus responseStatus = driverService.updateDriver(driver);
        return new ResponseEntity<>(driverService.findAll(), responseStatus);
    }
}
