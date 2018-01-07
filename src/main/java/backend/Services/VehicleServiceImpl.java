package backend.Services;

import backend.DTOs.VehicleDTO;
import backend.Entities.Vehicle;
import backend.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleDTO> findAll() {
        List<Vehicle> vehiclesInDatabase = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>(vehiclesInDatabase.size());

        for (Vehicle vehicle : vehiclesInDatabase) {
            vehicleDTOS.add(new VehicleDTO(
                    vehicle.getId(),
                    vehicle.getPlateNumbers(),
                    vehicle.getBrandAndModel(),
                    vehicle.getLeftTankConverter(),
                    vehicle.getRightTankConverter(),
                    vehicle.getLeftTankCapacity(),
                    vehicle.getRightTankCapacity(),
                    vehicle.isActive()
            ));
        }

        return vehicleDTOS;
    }

    @Override
    public HttpStatus addVehicle(Vehicle vehicle) {
        if (vehicleRepository.findByPlateNumbers(vehicle.getPlateNumbers()) != null) {
            return HttpStatus.CONFLICT;
        } else {
            vehicleRepository.save(vehicle);
            return HttpStatus.OK;
        }
    }

    @Override
    public HttpStatus updateVehicle(Vehicle vehicle) {
        if (vehicleRepository.findById(vehicle.getId()) == null) {
            return HttpStatus.CONFLICT;
        } else {
            Vehicle tempVehicle = vehicleRepository.findByPlateNumbers(vehicle.getPlateNumbers());
            if(tempVehicle == null || tempVehicle.getId() == vehicle.getId()) {
                vehicleRepository.save(vehicle);
                return HttpStatus.OK;
            }
            else{
                return HttpStatus.CONFLICT;
            }
        }
    }
}
