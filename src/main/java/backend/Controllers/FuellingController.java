package backend.Controllers;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.ImportedFuellingDTO;
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
    public FuellingController(FuellingServiceImpl fuellingService) {
        this.fuellingService = fuellingService;
    }


    @GetMapping(value = "/fuellings")
    public ResponseEntity getFuellings() {
        return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/fuelling/add")
    public ResponseEntity addFuelling(@RequestBody Fuelling fuelling) {
        ErrorMessageDTO message = fuellingService.addFuelling(fuelling);
        if (message == null) {
            return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @PutMapping(value = "/fuelling/update")
    public ResponseEntity updateFuelling(@RequestBody Fuelling fuelling) {
        ErrorMessageDTO message = fuellingService.updateFuelling(fuelling);
        if (message == null) {
            return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value = "/fuelling/delete/{id}")
    public ResponseEntity deleteFuelling(@PathVariable long id) {
        fuellingService.deleteFuelling(id);
        return new ResponseEntity<>(fuellingService.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/fuelling/import")
    public ResponseEntity importFuelling(@RequestBody List<ImportedFuellingDTO> fuellings) {
        return new ResponseEntity<>(fuellingService.importFuellings(fuellings), HttpStatus.OK);
    }
}
