package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class FuellingDTO {
    private long id;
    private double amount;
    private double grossValue;
    private String currency;
    private Timestamp date;
    private long supplierId;
    private String supplierName;
    private long vehicleId;
    private String vehiclePlateNumbers;
}
