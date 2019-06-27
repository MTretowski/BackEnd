package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
import backend.Entities.Measurment;
import backend.Services.MeasurmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MeasurmentController {

    private MeasurmentServiceImpl measurmentService;

    @Autowired
    public MeasurmentController(MeasurmentServiceImpl measurmentService) {
        this.measurmentService = measurmentService;
    }


    @GetMapping(value = "/measurments")
    public ResponseEntity getMeasurments() {
        return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/measurment/add")
    public ResponseEntity addMeasurments(@RequestBody Measurment measurment) {
        ErrorMessageDTO message = measurmentService.addMeasurment(measurment);
        if (message == null) {
            return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/measurment/update")
    public ResponseEntity updateMeasurments(@RequestBody Measurment measurment) {
        ErrorMessageDTO message = measurmentService.updateMeasurment(measurment);
        if (message == null) {
            return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value = "/measurment/delete/{id}")
    public ResponseEntity deleteMeasurments(@PathVariable long id) {
        ErrorMessageDTO message = measurmentService.deleteMeasurment(id);
        if (message == null) {
            return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }
}
