package backend.Services;

import backend.DTOs.DriverDTO;
import backend.Entities.Driver;
import backend.Repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpStatus addDriver(Driver driver) {
        if (driverRepository.findByFirstNameAndLastName(driver.getFirstName(), driver.getLastName()) != null) {
            return HttpStatus.CONFLICT;
        } else {
            driverRepository.save(driver);
            return HttpStatus.OK;
        }
    }

    @Override
    public HttpStatus updateDriver(Driver driver) {
        if (driverRepository.findById(driver.getId()) == null) {
            return HttpStatus.CONFLICT;
        } else {
            return addDriver(driver);
        }
    }
}