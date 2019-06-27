package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.VehicleDTO;
import backend.Entities.Vehicle;
import backend.Services.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleController {

    private VehicleServiceImpl vehicleService;

    @Autowired
    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }


    @GetMapping(value = "/vehicles")
    public ResponseEntity getVehicles() {
        return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/vehicle/add")
    public ResponseEntity addVehicle(@RequestBody Vehicle vehicle) {
        ErrorMessageDTO message = vehicleService.addVehicle(vehicle);
        if (message == null) {
            return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/vehicle/update")
    public ResponseEntity updateVehicle(@RequestBody Vehicle vehicle) {
        ErrorMessageDTO message = vehicleService.updateVehicle(vehicle);
        if (message == null) {
            return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
