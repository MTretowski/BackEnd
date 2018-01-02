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
    private Collection<Trip> tripsById;
    private Collection<Trip> tripsById_0;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurment that = (Measurment) o;

        if (id != that.id) return false;
        if (Double.compare(that.leftFuelTank, leftFuelTank) != 0) return false;
        if (Double.compare(that.rightFuelTank, rightFuelTank) != 0) return false;
        if (manualMeasurment != that.manualMeasurment) return false;
        if (returnToFull != that.returnToFull) return false;
        if (measuredAmount != that.measuredAmount) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(leftFuelTank);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rightFuelTank);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (manualMeasurment ? 1 : 0);
        result = 31 * result + (returnToFull ? 1 : 0);
        result = 31 * result + (measuredAmount ? 1 : 0);
        return result;
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
