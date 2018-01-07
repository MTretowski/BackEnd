package backend.Controllers;

import backend.DTOs.MeasurmentDTO;
import backend.Entities.Measurment;
import backend.Services.MeasurmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MeasurmentController {

    private MeasurmentServiceImpl measurmentService;

    @Autowired
    public MeasurmentController(MeasurmentServiceImpl measurmentService) {
        this.measurmentService = measurmentService;
    }


    @GetMapping(value = "/measurments")
    public ResponseEntity<List<MeasurmentDTO>> getMeasurments(){
        return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/measurment/add")
    public ResponseEntity<List<MeasurmentDTO>> addMeasurments(@RequestBody Measurment measurment){
        HttpStatus responseStatus = measurmentService.addMeasurment(measurment);
        return new ResponseEntity<>(measurmentService.findAll(), responseStatus);
    }


    @PutMapping(value = "/measurment/update")
    public ResponseEntity<List<MeasurmentDTO>> updateMeasurments(@RequestBody Measurment measurment){
        HttpStatus responseStatus = measurmentService.updateMeasurment(measurment);
        return new ResponseEntity<>(measurmentService.findAll(), responseStatus);
    }


    @DeleteMapping(value = "/measurment/delete/{id}")
    public ResponseEntity<List<MeasurmentDTO>> deleteMeasurments(@PathVariable long id){
        HttpStatus responseStatus = measurmentService.deleteMeasurment(id);
        return new ResponseEntity<>(measurmentService.findAll(), responseStatus);
    }
}
