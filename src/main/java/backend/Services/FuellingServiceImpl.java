package backend.Services;

import backend.DTOs.FuellingDTO;
import backend.DTOs.ImportedFuellingDTO;
import backend.Entities.Fuelling;
import backend.Repositories.FuellingRepository;
import backend.Repositories.SupplierRepository;
import backend.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FuellingServiceImpl implements FuellingService {

    private FuellingRepository fuellingRepository;
    private SupplierRepository supplierRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public FuellingServiceImpl(FuellingRepository fuellingRepository, SupplierRepository supplierRepository, VehicleRepository vehicleRepository) {
        this.fuellingRepository = fuellingRepository;
        this.supplierRepository = supplierRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<FuellingDTO> findAll() {
        List<Fuelling> fuellingsInDatabase = fuellingRepository.findAll();
        List<FuellingDTO> fuellingDTOS = new ArrayList<>(fuellingsInDatabase.size());

        for (Fuelling fuelling : fuellingsInDatabase) {
            fuellingDTOS.add(new FuellingDTO(
                    fuelling.getId(),
                    fuelling.getAmount(),
                    fuelling.getGrossValue(),
                    fuelling.getCurrency(),
                    fuelling.getDate(),
                    fuelling.getSupplierId(),
                    supplierRepository.findById(fuelling.getSupplierId()).getName(),
                    fuelling.getVehicleId(),
                    vehicleRepository.findById(fuelling.getVehicleId()).getPlateNumbers()
            ));
        }

        return fuellingDTOS;
    }

    @Override
    public HttpStatus addFuelling(Fuelling fuelling) {
        if (fuellingRepository.findByDateAndVehicleIdAndAmount(fuelling.getDate(), fuelling.getVehicleId(), fuelling.getAmount()) != null) {
            return HttpStatus.CONFLICT;
        } else {
            fuellingRepository.save(fuelling);
            return HttpStatus.OK;
        }
    }

    @Override
    public HttpStatus updateFuelling(Fuelling fuelling) {
        if (fuellingRepository.findById(fuelling.getId()) == null) {
            return HttpStatus.CONFLICT;
        } else {
            fuellingRepository.save(fuelling);
            return HttpStatus.OK;
        }
    }

    @Override
    public void deleteFuelling(long id) {
        fuellingRepository.deleteById(id);
    }

    @Override
    public List<List<FuellingDTO>> importFuellings(List<ImportedFuellingDTO> fuellings) {

        List<FuellingDTO> successfullyImportedFuellings = new ArrayList<>();
        List<FuellingDTO> unsuccessfullyImportedFuellings = new ArrayList<>();
        FuellingDTO fuellingDTO;
        Fuelling fuelling;
        List<Fuelling> fuellingsToSaveInDatabase = new ArrayList<>();

        for (ImportedFuellingDTO importedFuelling : fuellings) {

            fuellingDTO = new FuellingDTO(
                    0,
                    importedFuelling.getAmount(),
                    importedFuelling.getGrossValue(),
                    importedFuelling.getCurrency(),
                    importedFuelling.getDate(),
                    supplierRepository.findByName(importedFuelling.getSupplier()).getId(),
                    supplierRepository.findByName(importedFuelling.getSupplier()).getName(),
                    importedFuelling.getVehicleId(),
                    vehicleRepository.findById(importedFuelling.getVehicleId()).getPlateNumbers()
            );
            fuelling = new Fuelling();
            fuelling.setAmount(importedFuelling.getAmount());
            fuelling.setGrossValue(importedFuelling.getGrossValue());
            fuelling.setCurrency(importedFuelling.getCurrency());
            fuelling.setDate(importedFuelling.getDate());
            fuelling.setSupplierId(supplierRepository.findByName(importedFuelling.getSupplier()).getId());
            fuelling.setVehicleId(importedFuelling.getVehicleId());

            if (fuellingRepository.findByDateAndVehicleIdAndAmount(fuelling.getDate(), fuelling.getVehicleId(), fuelling.getAmount()) != null) {
                unsuccessfullyImportedFuellings.add(fuellingDTO);
            } else {
                fuellingsToSaveInDatabase.add(fuelling);
                successfullyImportedFuellings.add(fuellingDTO);
            }
        }
        fuellingRepository.save(fuellingsToSaveInDatabase);
        List<List<FuellingDTO>> importSummary = new ArrayList<>(3);
        importSummary.add(successfullyImportedFuellings);
        importSummary.add(unsuccessfullyImportedFuellings);
        importSummary.add(findAll());

        return importSummary;
    }
}
