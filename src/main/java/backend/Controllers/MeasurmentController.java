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

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/measurments")
    public ResponseEntity<List<MeasurmentDTO>> getMeasurments(){
        return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/measurment/add")
    public ResponseEntity<List<MeasurmentDTO>> addMeasurments(@RequestBody Measurment measurment){
        HttpStatus responseStatus = measurmentService.addMeasurment(measurment);
        return new ResponseEntity<>(measurmentService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/measurment/update")
    public ResponseEntity<List<MeasurmentDTO>> updateMeasurments(@RequestBody Measurment measurment){
        HttpStatus responseStatus = measurmentService.updateMeasurment(measurment);
        return new ResponseEntity<>(measurmentService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/measurment/delete")
    public ResponseEntity<List<MeasurmentDTO>> deleteMeasurments(@RequestBody long id){
        measurmentService.deleteMeasurment(id);
        return new ResponseEntity<>(measurmentService.findAll(), HttpStatus.OK);
    }
}
