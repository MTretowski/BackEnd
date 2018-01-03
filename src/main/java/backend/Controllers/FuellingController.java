package backend.Controllers;

import backend.DTOs.FuellingDTO;
import backend.Entities.Fuelling;
import backend.Services.FuellingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FuellingController {

    private FuellingServiceImpl fuellingService;

    @Autowired
    public FuellingController(FuellingServiceImpl fuellingService){
        this.fuellingService = fuellingService;
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping(value = "/fuellings")
    public ResponseEntity<List<FuellingDTO>> getFuellings(){
        return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/fuelling/add")
    public ResponseEntity<List<FuellingDTO>> addFuelling(@RequestBody Fuelling fuelling){
        HttpStatus responseStatus = fuellingService.addFuelling(fuelling);
        return new ResponseEntity<>(fuellingService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PutMapping(value = "/fuelling/update")
    public ResponseEntity<List<FuellingDTO>> updateFuelling(@RequestBody Fuelling fuelling){
        HttpStatus responseStatus = fuellingService.updateFuelling(fuelling);
        return new ResponseEntity<>(fuellingService.findAll(), responseStatus);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @DeleteMapping(value = "/fuelling/delete")
    public ResponseEntity<List<FuellingDTO>> deleteFuelling(@RequestBody long id){
        fuellingService.deleteFuelling(id);
        return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @PostMapping(value = "/fuelling/import")
    public ResponseEntity<List<List<FuellingDTO>>> importFuelling(@RequestBody List<Fuelling> fuellings){
        return new ResponseEntity<>(fuellingService.importFuellings(fuellings), HttpStatus.OK);
    }
}
