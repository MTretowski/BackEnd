package backend.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Vehicle {
    private long id;
    private String plateNumbers;
    private String brandAndModel;
    private Double leftTankConverter;
    private Double rightTankConverter;
    private Double leftTankCapacity;
    private Double rightTankCapacity;
    private boolean active;
    private Collection<Fuelling> fuellingsById;
    private Collection<Measurment> measurmentsById;
    private Collection<Trip> tripsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "plate_numbers")
    public String getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(String plateNumbers) {
        this.plateNumbers = plateNumbers;
    }

    @Basic
    @Column(name = "brand_and_model")
    public String getBrandAndModel() {
        return brandAndModel;
    }

    public void setBrandAndModel(String brandAndModel) {
        this.brandAndModel = brandAndModel;
    }

    @Basic
    @Column(name = "left_tank_converter")
    public Double getLeftTankConverter() {
        return leftTankConverter;
    }

    public void setLeftTankConverter(Double leftTankConverter) {
        this.leftTankConverter = leftTankConverter;
    }

    @Basic
    @Column(name = "right_tank_converter")
    public Double getRightTankConverter() {
        return rightTankConverter;
    }

    public void setRightTankConverter(Double rightTankConverter) {
        this.rightTankConverter = rightTankConverter;
    }

    @Basic
    @Column(name = "left_tank_capacity")
    public Double getLeftTankCapacity() {
        return leftTankCapacity;
    }

    public void setLeftTankCapacity(Double leftTankCapacity) {
        this.leftTankCapacity = leftTankCapacity;
    }

    @Basic
    @Column(name = "right_tank_capacity")
    public Double getRightTankCapacity() {
        return rightTankCapacity;
    }

    public void setRightTankCapacity(Double rightTankCapacity) {
        this.rightTankCapacity = rightTankCapacity;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "vehicleByVehicleId")
    public Collection<Fuelling> getFuellingsById() {
        return fuellingsById;
    }

    public void setFuellingsById(Collection<Fuelling> fuellingsById) {
        this.fuellingsById = fuellingsById;
    }

    @OneToMany(mappedBy = "vehicleByVehicleId")
    public Collection<Measurment> getMeasurmentsById() {
        return measurmentsById;
    }

    public void setMeasurmentsById(Collection<Measurment> measurmentsById) {
        this.measurmentsById = measurmentsById;
    }

    @OneToMany(mappedBy = "vehicleByVehicleId")
    public Collection<Trip> getTripsById() {
        return tripsById;
    }

    public void setTripsById(Collection<Trip> tripsById) {
        this.tripsById = tripsById;
    }
}
