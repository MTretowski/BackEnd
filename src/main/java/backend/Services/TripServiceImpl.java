package backend.Services;

import backend.DTOs.TripDTO;
import backend.Entities.Trip;
import backend.Repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<TripDTO> findAll() {
        List<Trip> tripsInDatabase = tripRepository.findAll();
        List<TripDTO> tripDTOS = new ArrayList<>(tripsInDatabase.size());

        for (Trip trip : tripsInDatabase) {
            tripDTOS.add(new TripDTO(
                    trip.getId(),
                    trip.getBusinnesTripNumber(),
                    trip.getStartDate(),
                    trip.getEndDate(),
                    trip.getDistance(),
                    trip.getUsedFuelCan(),
                    trip.getUsedFuelWebasto(),
                    trip.getFuelConsumptionByGps(),
                    trip.getRealFuelConsumption(),
                    trip.getComment(),
                    trip.getVehicleId(),
                    trip.getFirstDriverId(),
                    trip.getFirstDriverId(),
                    trip.getStartingMeasurmentId(),
                    trip.getEndingMeasurmentId()
            ));
        }
        return tripDTOS;
    }

    @Override
    public HttpStatus addTrip(Trip trip) {
        List<Trip> tripsInDatabase = tripRepository.findAllByVehicleId(trip.getVehicleId());
        for (Trip tripInDatabase : tripsInDatabase) {
            if (checkTripDates(tripInDatabase, trip)) {
                tripRepository.save(trip);
                return HttpStatus.OK;
            }
        }
        return HttpStatus.CONFLICT;
    }

    @Override
    public HttpStatus updateTrip(Trip trip) {
        if (tripRepository.findById(trip.getId()) == null) {
            return HttpStatus.CONFLICT;
        } else {
            tripRepository.save(trip);
            return HttpStatus.OK;
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
