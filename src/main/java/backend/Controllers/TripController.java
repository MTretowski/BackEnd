package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
import backend.Entities.Trip;
import backend.Services.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TripController {

    private TripServiceImpl tripService;

    @Autowired
    public TripController(TripServiceImpl tripService) {
        this.tripService = tripService;
    }


    @GetMapping(value = "/trips")
    public ResponseEntity getTrips() {
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/trip/add")
    public ResponseEntity addTrip(@RequestBody Trip trip) {
        ErrorMessageDTO message = tripService.addTrip(trip);
        if (message == null) {
            return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/trip/update")
    public ResponseEntity updateTrip(@RequestBody Trip trip) {
        ErrorMessageDTO message = tripService.updateTrip(trip);
        if (message == null) {
            return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value = "/trip/delete/{id}")
    public ResponseEntity updateTrip(@PathVariable long id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }
}
