package backend.Services;

import backend.DTOs.VehicleDTO;
import backend.Entities.Vehicle;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();

    HttpStatus addVehicle(Vehicle vehicle);

    HttpStatus updateVehicle(Vehicle vehicle);
}
