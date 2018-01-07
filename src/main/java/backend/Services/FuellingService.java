package backend.Services;

import backend.DTOs.FuellingDTO;
import backend.DTOs.ImportedFuellingDTO;
import backend.Entities.Fuelling;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface FuellingService {

    List<FuellingDTO> findAll();

    HttpStatus addFuelling(Fuelling fuelling);

    HttpStatus updateFuelling(Fuelling fuelling);

    void deleteFuelling(long id);

    List<List<FuellingDTO>> importFuellings(List<ImportedFuellingDTO> fuellings);
}
