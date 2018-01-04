package backend.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Trip {
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
    private long firstDriverId;
    private long secondDriverId;
    private long startingMeasurmentId;
    private long endingMeasurmentId;
    private Vehicle vehicleByVehicleId;
    private Driver driverByFirstDriverId;
    private Driver driverBySecondDriverId;
    private Measurment measurmentByStartingMeasurmentId;
    private Measurment measurmentByEndingMeasurmentId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "business_trip_number")
    public String getBusinessTripNumber() {
        return businessTripNumber;
    }

    public void setBusinessTripNumber(String businessTripNumber) {
        this.businessTripNumber = businessTripNumber;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "used_fuel_can")
    public double getUsedFuelCan() {
        return usedFuelCan;
    }

    public void setUsedFuelCan(double usedFuelCan) {
        this.usedFuelCan = usedFuelCan;
    }

    @Basic
    @Column(name = "used_fuel_webasto")
    public double getUsedFuelWebasto() {
        return usedFuelWebasto;
    }

    public void setUsedFuelWebasto(double usedFuelWebasto) {
        this.usedFuelWebasto = usedFuelWebasto;
    }

    @Basic
    @Column(name = "fuel_consumption_by_gps")
    public double getFuelConsumptionByGps() {
        return fuelConsumptionByGps;
    }

    public void setFuelConsumptionByGps(double fuelConsumptionByGps) {
        this.fuelConsumptionByGps = fuelConsumptionByGps;
    }

    @Basic
    @Column(name = "real_fuel_consumption")
    public double getRealFuelConsumption() {
        return realFuelConsumption;
    }

    public void setRealFuelConsumption(double realFuelConsumption) {
        this.realFuelConsumption = realFuelConsumption;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "vehicle_id")
    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Basic
    @Column(name = "first_driver_id")
    public long getFirstDriverId() {
        return firstDriverId;
    }

    public void setFirstDriverId(long firstDriverId) {
        this.firstDriverId = firstDriverId;
    }

    @Basic
    @Column(name = "second_driver_id")
    public long getSecondDriverId() {
        return secondDriverId;
    }

    public void setSecondDriverId(long secondDriverId) {
        this.secondDriverId = secondDriverId;
    }

    @Basic
    @Column(name = "starting_measurment_id")
    public long getStartingMeasurmentId() {
        return startingMeasurmentId;
    }

    public void setStartingMeasurmentId(long startingMeasurmentId) {
        this.startingMeasurmentId = startingMeasurmentId;
    }

    @Basic
    @Column(name = "ending_measurment_id")
    public long getEndingMeasurmentId() {
        return endingMeasurmentId;
    }

    public void setEndingMeasurmentId(long endingMeasurmentId) {
        this.endingMeasurmentId = endingMeasurmentId;
    }

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Vehicle getVehicleByVehicleId() {
        return vehicleByVehicleId;
    }

    public void setVehicleByVehicleId(Vehicle vehicleByVehicleId) {
        this.vehicleByVehicleId = vehicleByVehicleId;
    }

    @ManyToOne
    @JoinColumn(name = "first_driver_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Driver getDriverByFirstDriverId() {
        return driverByFirstDriverId;
    }

    public void setDriverByFirstDriverId(Driver driverByFirstDriverId) {
        this.driverByFirstDriverId = driverByFirstDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "second_driver_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Driver getDriverBySecondDriverId() {
        return driverBySecondDriverId;
    }

    public void setDriverBySecondDriverId(Driver driverBySecondDriverId) {
        this.driverBySecondDriverId = driverBySecondDriverId;
    }

    @ManyToOne
    @JoinColumn(name = "starting_measurment_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Measurment getMeasurmentByStartingMeasurmentId() {
        return measurmentByStartingMeasurmentId;
    }

    public void setMeasurmentByStartingMeasurmentId(Measurment measurmentByStartingMeasurmentId) {
        this.measurmentByStartingMeasurmentId = measurmentByStartingMeasurmentId;
    }

    @ManyToOne
    @JoinColumn(name = "ending_measurment_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Measurment getMeasurmentByEndingMeasurmentId() {
        return measurmentByEndingMeasurmentId;
    }

    public void setMeasurmentByEndingMeasurmentId(Measurment measurmentByEndingMeasurmentId) {
        this.measurmentByEndingMeasurmentId = measurmentByEndingMeasurmentId;
    }
}
