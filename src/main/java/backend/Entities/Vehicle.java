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
    private Collection<Trip> tripsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (active != vehicle.active) return false;
        if (plateNumbers != null ? !plateNumbers.equals(vehicle.plateNumbers) : vehicle.plateNumbers != null) return false;
        if (brandAndModel != null ? !brandAndModel.equals(vehicle.brandAndModel) : vehicle.brandAndModel != null) return false;
        if (leftTankConverter != null ? !leftTankConverter.equals(vehicle.leftTankConverter) : vehicle.leftTankConverter != null) return false;
        if (rightTankConverter != null ? !rightTankConverter.equals(vehicle.rightTankConverter) : vehicle.rightTankConverter != null) return false;
        if (leftTankCapacity != null ? !leftTankCapacity.equals(vehicle.leftTankCapacity) : vehicle.leftTankCapacity != null) return false;
        if (rightTankCapacity != null ? !rightTankCapacity.equals(vehicle.rightTankCapacity) : vehicle.rightTankCapacity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (plateNumbers != null ? plateNumbers.hashCode() : 0);
        result = 31 * result + (brandAndModel != null ? brandAndModel.hashCode() : 0);
        result = 31 * result + (leftTankConverter != null ? leftTankConverter.hashCode() : 0);
        result = 31 * result + (rightTankConverter != null ? rightTankConverter.hashCode() : 0);
        result = 31 * result + (leftTankCapacity != null ? leftTankCapacity.hashCode() : 0);
        result = 31 * result + (rightTankCapacity != null ? rightTankCapacity.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @OneToMany(mappedBy = "vehicleByVehicleId")
    public Collection<Fuelling> getFuellingsById() {
        return fuellingsById;
    }

    public void setFuellingsById(Collection<Fuelling> fuellingsById) {
        this.fuellingsById = fuellingsById;
    }

    @OneToMany(mappedBy = "vehicleByVehicleId")
    public Collection<Trip> getTripsById() {
        return tripsById;
    }

    public void setTripsById(Collection<Trip> tripsById) {
        this.tripsById = tripsById;
    }
}
