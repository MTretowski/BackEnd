package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.FuellingDTO;
import backend.DTOs.ImportedFuellingDTO;
import backend.Entities.Fuelling;

import java.util.List;

public interface FuellingService {

    List<FuellingDTO> findAll();

    ErrorMessageDTO addFuelling(Fuelling fuelling);

    ErrorMessageDTO updateFuelling(Fuelling fuelling);

    void deleteFuelling(long id);

    List<List<FuellingDTO>> importFuellings(List<ImportedFuellingDTO> fuellings);
}
