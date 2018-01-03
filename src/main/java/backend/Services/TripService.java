package backend.Services;

import backend.DTOs.TripDTO;
import backend.Entities.Trip;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface TripService {

    List<TripDTO> findAll();

    HttpStatus addTrip(Trip trip);

    HttpStatus updateTrip(Trip trip);

    void deleteTrip(long id);

}
