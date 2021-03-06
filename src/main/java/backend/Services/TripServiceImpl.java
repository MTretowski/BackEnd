package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.TripDTO;
import backend.Entities.Trip;
import backend.Repositories.DriverRepository;
import backend.Repositories.TripRepository;
import backend.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;
    private VehicleRepository vehicleRepository;
    private DriverRepository driverRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<TripDTO> findAll() {
        List<Trip> tripsInDatabase = tripRepository.findAll();
        List<TripDTO> tripDTOS = new ArrayList<>(tripsInDatabase.size());

        for (Trip trip : tripsInDatabase) {
            tripDTOS.add(new TripDTO(
                    trip.getId(),
                    trip.getBusinessTripNumber(),
                    trip.getStartDate(),
                    trip.getEndDate(),
                    trip.getDistance(),
                    trip.getUsedFuelCan(),
                    trip.getUsedFuelWebasto(),
                    trip.getFuelConsumptionByGps(),
                    trip.getRealFuelConsumption(),
                    trip.getComment(),
                    trip.getVehicleId(),
                    vehicleRepository.findById(trip.getVehicleId()).getPlateNumbers(),
                    trip.getDriverId(),
                    (driverRepository.findById(trip.getDriverId()).getFirstName() + " " + driverRepository.findById(trip.getDriverId()).getLastName()),
                    trip.getStartingMeasurmentId(),
                    trip.getEndingMeasurmentId()
            ));
        }
        return tripDTOS;
    }

    @Override
    public ErrorMessageDTO addTrip(Trip trip) {
        List<Trip> tripsInDatabase = tripRepository.findAllByVehicleId(trip.getVehicleId());
        if (tripsInDatabase.size() == 0) {
            tripRepository.save(trip);
            return null;
        } else {
            for (Trip tripInDatabase : tripsInDatabase) {
                if (!checkTripDates(tripInDatabase, trip)) {
                    return new ErrorMessageDTO("W bazie danych odnaleziono trasę tego pojazdu, której czas trwania pokrywa się z okresem trwania dodawanej trasy.");
                }
            }
            tripRepository.save(trip);
            return null;
        }

    }

    @Override
    public ErrorMessageDTO updateTrip(Trip trip) {
        if (tripRepository.findById(trip.getId()) == null) {
            return new ErrorMessageDTO("Nie odnaleziono trasy o podanym identyfikatorze - prawdopodobnie została usunięta. Odśwież aplikację i spróbuj ponownie.");
        } else {
            List<Trip> tripsInDatabase = tripRepository.findAllByVehicleId(trip.getVehicleId());
            if (tripsInDatabase.size() == 0) {
                tripRepository.save(trip);
                return null;
            } else {
                for (Trip tripInDatabase : tripsInDatabase) {
                    if (!checkTripDates(tripInDatabase, trip)) {
                        if (tripInDatabase.getId() != trip.getId()) {
                            return new ErrorMessageDTO("W bazie danych odnaleziono trasę tego pojazdu, której czas trwania pokrywa się z okresem trwania dodawanej trasy.");
                        }
                    }
                }
                tripRepository.save(trip);
                return null;
            }
        }
    }

    @Override
    public void deleteTrip(long id) {
        tripRepository.deleteById(id);
    }

    private boolean checkTripDates(Trip tripInDatabase, Trip newTrip) {
        Timestamp tripInDBStart = tripInDatabase.getStartDate();
        Timestamp tripInDBEnd = tripInDatabase.getEndDate();
        Timestamp newTripStart = newTrip.getStartDate();
        Timestamp newTripEnd = newTrip.getEndDate();

        if ((newTripStart.before(tripInDBStart) && newTripStart.before(tripInDBEnd) && newTripEnd.after(tripInDBStart) && newTripEnd.after(tripInDBEnd)) ||
                (newTripStart.before(tripInDBStart) && newTripStart.before(tripInDBEnd) && newTripEnd.after(tripInDBStart) && newTripEnd.before(tripInDBEnd)) ||
                (newTripStart.after(tripInDBStart) && newTripStart.before(tripInDBEnd) && newTripEnd.after(tripInDBStart) && newTripEnd.after(tripInDBEnd)) ||
                (newTripStart.before(tripInDBStart) && newTripStart.before(tripInDBEnd) && newTripEnd.after(tripInDBStart) && newTripEnd.before(tripInDBEnd)) ||
                (newTripStart.equals(tripInDBStart) || newTripEnd.equals(tripInDBEnd))) {
            return false;
        } else {
            return true;
        }
    }
}
