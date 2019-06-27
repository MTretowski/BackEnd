package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.FuellingDTO;
import backend.DTOs.ImportedFuellingDTO;
import backend.Entities.Fuelling;
import backend.Repositories.FuellingRepository;
import backend.Repositories.SupplierRepository;
import backend.Repositories.VehicleRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
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

    private void test(){
        System.out.println("test");
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
    public ErrorMessageDTO addFuelling(Fuelling fuelling) {
        if (fuellingRepository.findByDateAndVehicleId(fuelling.getDate(), fuelling.getVehicleId()) != null) {
            return new ErrorMessageDTO("W bazie danych zostało znalezione takie tankowanie.");
        } else {
            fuellingRepository.save(fuelling);
            return null;
        }
    }

    @Override
    public ErrorMessageDTO updateFuelling(Fuelling fuelling) {
        if (fuellingRepository.findById(fuelling.getId()) == null) {
            return new ErrorMessageDTO("Nie odnaleziono tankowania o podanym identyfikatorze - prawdopodobnie zostało usunięte. Odśwież aplikację i spróbuj ponownie.");
        } else {
            Fuelling fuellingTemp = fuellingRepository.findByDateAndVehicleId(fuelling.getDate(), fuelling.getVehicleId());
            if(fuellingTemp == null || fuelling.getId() == fuellingTemp.getId()) {
                fuellingRepository.save(fuelling);
                return null;
            }
            else{
                return new ErrorMessageDTO("W bazie danych zostało znalezione takie tankowanie.");
            }
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

        for (ImportedFuellingDTO importedFuelling : fuellings) {

            fuellingDTO = new FuellingDTO(
                    0,
                    Precision.round(importedFuelling.getAmount(),2),
                    Precision.round(importedFuelling.getGrossValue(),2),
                    importedFuelling.getCurrency(),
                    importedFuelling.getDate(),
                    supplierRepository.findByName(importedFuelling.getSupplier()).getId(),
                    supplierRepository.findByName(importedFuelling.getSupplier()).getName(),
                    importedFuelling.getVehicleId(),
                    vehicleRepository.findById(importedFuelling.getVehicleId()).getPlateNumbers()
            );
            fuelling = new Fuelling();
            fuelling.setAmount(Precision.round(importedFuelling.getAmount(),2));
            fuelling.setGrossValue(Precision.round(importedFuelling.getGrossValue(),2));
            fuelling.setCurrency(importedFuelling.getCurrency());
            fuelling.setDate(importedFuelling.getDate());
            fuelling.setSupplierId(supplierRepository.findByName(importedFuelling.getSupplier()).getId());
            fuelling.setVehicleId(importedFuelling.getVehicleId());

            if (fuellingRepository.findByDateAndVehicleId(importedFuelling.getDate(), importedFuelling.getVehicleId()) != null) {
                unsuccessfullyImportedFuellings.add(fuellingDTO);
            } else {
                fuellingRepository.save(fuelling);
                successfullyImportedFuellings.add(fuellingDTO);
            }
        }

        List<List<FuellingDTO>> importSummary = new ArrayList<>(3);
        importSummary.add(successfullyImportedFuellings);
        importSummary.add(unsuccessfullyImportedFuellings);
        importSummary.add(findAll());

        return importSummary;
    }
}
