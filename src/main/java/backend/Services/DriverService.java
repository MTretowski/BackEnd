package backend.Services;

import backend.DTOs.DriverDTO;
import backend.Entities.Driver;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface DriverService {

    List<DriverDTO> findAll();

    HttpStatus addDriver(Driver driver);

    HttpStatus updateDriver(Driver driver);
}
