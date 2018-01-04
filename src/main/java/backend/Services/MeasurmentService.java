package backend.Services;

import backend.DTOs.MeasurmentDTO;
import backend.Entities.Measurment;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface MeasurmentService {

    List<MeasurmentDTO> findAll();

    HttpStatus addMeasurment(Measurment measurment);

    HttpStatus updateMeasurment(Measurment measurment);

    HttpStatus deleteMeasurment(long id);
}
