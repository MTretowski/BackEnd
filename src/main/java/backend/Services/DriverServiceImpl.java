package backend.Services;

import backend.DTOs.DriverDTO;
import backend.DTOs.ErrorMessageDTO;
import backend.Entities.Driver;
import backend.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    private void test(){
        System.out.println("test");
    }

    @Override
    public List<DriverDTO> findAll() {

        List<Driver> driversInDatabase = driverRepository.findAll();
        List<DriverDTO> driverDTOS = new ArrayList<>(driversInDatabase.size());

        for (Driver driver : driversInDatabase) {
            driverDTOS.add(new DriverDTO(
                    driver.getId(),
                    driver.getFirstName(),
                    driver.getLastName(),
                    driver.isActive()
            ));
        }
        return driverDTOS;
    }

    @Override
    public ErrorMessageDTO addDriver(Driver driver) {
        if (driverRepository.findByFirstNameAndLastName(driver.getFirstName(), driver.getLastName()) != null) {
            return new ErrorMessageDTO("W bazie danych został znaleziony kierowca o takim imieniu i nazwisku.");
        } else {
            driverRepository.save(driver);
            return null;
        }
    }

    @Override
    public ErrorMessageDTO updateDriver(Driver driver) {
        if (driverRepository.findById(driver.getId()) == null) {
            return new ErrorMessageDTO("Nie odnaleziono kierowcy o podanym identyfikatorze - prawdopodobnie został usunięty. Odśwież aplikację i spróbuj ponownie.");
        } else {
            Driver driverTemp = driverRepository.findByFirstNameAndLastName(driver.getFirstName(), driver.getLastName());
            if (driverTemp == null || driverTemp.getId() == driver.getId()) {
                driverRepository.save(driver);
                return null;
            } else {
                return new ErrorMessageDTO("W bazie danych został znaleziony kierowca o takim imieniu i nazwisku.");
            }
        }
    }
}