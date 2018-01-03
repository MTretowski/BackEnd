package backend.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class TripDTO {
    private long id;
    private String businnesTripNumber;
    private Timestamp startDate;
    private Timestamp endDate;
    private double distance;
    private double usedFuelCan;
    private double usedFuelWebasto;
    private double fuelConsumptionByGps;
    private double realFuelConsumption;
    private String comment;
    private long vehicleId;
    private long firstDriverId;
    private long secondDriverId;
    private long startingMeasurmentId;
    private long endingMeasurmentId;
}
