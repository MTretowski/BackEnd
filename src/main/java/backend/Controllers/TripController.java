package backend.Controllers;

import backend.DTOs.TripDTO;
import backend.Entities.Trip;
import backend.Services.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TripController {

    private TripServiceImpl tripService;

    @Autowired
    public TripController(TripServiceImpl tripService){
        this.tripService = tripService;
    }


    @GetMapping(value = "/trips")
    public ResponseEntity<List<TripDTO>> getTrips(){
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/trip/add")
    public ResponseEntity<List<TripDTO>> addTrip(@RequestBody Trip trip){
        HttpStatus responseStatus = tripService.addTrip(trip);
        return new ResponseEntity<>(tripService.findAll(), responseStatus);
    }


    @PutMapping(value = "/trip/update")
    public ResponseEntity<List<TripDTO>> updateTrip(@RequestBody Trip trip){
        HttpStatus responseStatus = tripService.updateTrip(trip);
        return new ResponseEntity<>(tripService.findAll(), responseStatus);
    }


    @DeleteMapping(value = "/trip/delete/{id}")
    public ResponseEntity<List<TripDTO>> updateTrip(@PathVariable long id){
        tripService.deleteTrip(id);
        return new ResponseEntity<>(tripService.findAll(), HttpStatus.OK);
    }
}
