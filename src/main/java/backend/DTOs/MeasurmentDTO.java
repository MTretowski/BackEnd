package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class MeasurmentDTO {
    private long id;
    private Timestamp date;
    private double leftFuelTank;
    private double rightFuelTank;
    private double leftFuelTankAmount;
    private double rightFuelTankAmount;
    private boolean manualMeasurment;
    private boolean returnToFull;
    private boolean measuredAmount;
    private long vehicleId;
    private String vehiclePlateNumers;
    private String startedBusinessTrip;
    private String endedBusinessTrip;
}
