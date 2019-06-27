package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.MeasurmentDTO;
import backend.Entities.Measurment;

import java.util.List;

public interface MeasurmentService {

    List<MeasurmentDTO> findAll();

    ErrorMessageDTO addMeasurment(Measurment measurment);

    ErrorMessageDTO updateMeasurment(Measurment measurment);

    ErrorMessageDTO deleteMeasurment(long id);
}
