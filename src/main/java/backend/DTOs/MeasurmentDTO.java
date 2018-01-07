package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@Getter
public class MeasurmentDTO {
    private long id;
    private Date date;
    private double leftFuelTank;
    private double rightFuelTank;
    private double leftFuelTankAmount;
    private double rightFuelTankAmount;
    private boolean manualMeasurment;
    private boolean returnToFull;
    private boolean measuredAmount;
    private String measurmentWay;
    private long vehicleId;
    private String vehiclePlateNumbers;
    private String startedBusinessTrip;
    private String endedBusinessTrip;
}
