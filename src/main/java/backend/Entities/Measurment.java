package backend.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Measurment {
    private long id;
    private Timestamp date;
    private double leftFuelTank;
    private double rightFuelTank;
    private boolean manualMeasurment;
    private boolean returnToFull;
    private boolean measuredAmount;
    private long vehicleId;
    private Vehicle vehicleByVehicleId;
    private Collection<Trip> tripsById;
    private Collection<Trip> tripsById_0;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "left_fuel_tank")
    public double getLeftFuelTank() {
        return leftFuelTank;
    }

    public void setLeftFuelTank(double leftFuelTank) {
        this.leftFuelTank = leftFuelTank;
    }

    @Basic
    @Column(name = "right_fuel_tank")
    public double getRightFuelTank() {
        return rightFuelTank;
    }

    public void setRightFuelTank(double rightFuelTank) {
        this.rightFuelTank = rightFuelTank;
    }

    @Basic
    @Column(name = "manual_measurment")
    public boolean isManualMeasurment() {
        return manualMeasurment;
    }

    public void setManualMeasurment(boolean manualMeasurment) {
        this.manualMeasurment = manualMeasurment;
    }

    @Basic
    @Column(name = "return_to_full")
    public boolean isReturnToFull() {
        return returnToFull;
    }

    public void setReturnToFull(boolean returnToFull) {
        this.returnToFull = returnToFull;
    }

    @Basic
    @Column(name = "measured_amount")
    public boolean isMeasuredAmount() {
        return measuredAmount;
    }

    public void setMeasuredAmount(boolean measuredAmount) {
        this.measuredAmount = measuredAmount;
    }

    @Basic
    @Column(name = "vehicle_id")
    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Vehicle getVehicleByVehicleId() {
        return vehicleByVehicleId;
    }

    public void setVehicleByVehicleId(Vehicle vehicleByVehicleId) {
        this.vehicleByVehicleId = vehicleByVehicleId;
    }

    @OneToMany(mappedBy = "measurmentByStartingMeasurmentId")
    public Collection<Trip> getTripsById() {
        return tripsById;
    }

    public void setTripsById(Collection<Trip> tripsById) {
        this.tripsById = tripsById;
    }

    @OneToMany(mappedBy = "measurmentByEndingMeasurmentId")
    public Collection<Trip> getTripsById_0() {
        return tripsById_0;
    }

    public void setTripsById_0(Collection<Trip> tripsById_0) {
        this.tripsById_0 = tripsById_0;
    }
}
