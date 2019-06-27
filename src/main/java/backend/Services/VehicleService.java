package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.VehicleDTO;
import backend.Entities.Vehicle;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();

    ErrorMessageDTO addVehicle(Vehicle vehicle);

    ErrorMessageDTO updateVehicle(Vehicle vehicle);
}
