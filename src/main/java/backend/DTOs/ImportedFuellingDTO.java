package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class ImportedFuellingDTO {
    private double amount;
    private double grossValue;
    private String currency;
    private Timestamp date;
    private String vehicle;
    private long vehicleId;
    private String description;
    private String additionalDescription;
    private String message;
    private String supplier;
}
