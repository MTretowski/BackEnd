package backend.Controllers;

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
    public VehicleController(VehicleServiceImpl vehicleService){
        this.vehicleService = vehicleService;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<VehicleDTO>> getVehicles(){
        return new ResponseEntity<>(vehicleService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/vehicle/add")
    public ResponseEntity<List<VehicleDTO>> addVehicle(@RequestBody Vehicle vehicle){
        HttpStatus responseStatus = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(vehicleService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/vehicle/update")
    public ResponseEntity<List<VehicleDTO>> updateVehicle(@RequestBody Vehicle vehicle){
        HttpStatus responseStatus = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(vehicleService.findAll(), responseStatus);
    }
}
