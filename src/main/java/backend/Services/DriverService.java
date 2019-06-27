package backend.Services;

import backend.DTOs.DriverDTO;
import backend.DTOs.ErrorMessageDTO;
import backend.Entities.Driver;

import java.util.List;

public interface DriverService {

    List<DriverDTO> findAll();

    ErrorMessageDTO addDriver(Driver driver);

    ErrorMessageDTO updateDriver(Driver driver);
}
