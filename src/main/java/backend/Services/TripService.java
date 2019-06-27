package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.TripDTO;
import backend.Entities.Trip;

import java.util.List;

public interface TripService {

    List<TripDTO> findAll();

    ErrorMessageDTO addTrip(Trip trip);

    ErrorMessageDTO updateTrip(Trip trip);

    void deleteTrip(long id);

}
