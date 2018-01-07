package backend.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class TripDTO {
    private long id;
    private String businessTripNumber;
    private Timestamp startDate;
    private Timestamp endDate;
    private double distance;
    private double usedFuelCan;
    private double usedFuelWebasto;
    private double fuelConsumptionByGps;
    private double realFuelConsumption;
    private String comment;
    private long vehicleId;
    private String vehiclePlateNumbers;
    private long driverId;
    private String driverName;
    private long startingMeasurmentId;
    private long endingMeasurmentId;
}
