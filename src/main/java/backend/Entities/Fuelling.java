package backend.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Fuelling {
    private long id;
    private double amount;
    private double grossValue;
    private String currency;
    private Timestamp date;
    private long supplierId;
    private long vehicleId;
    private Supplier supplierBySupplierId;
    private Vehicle vehicleByVehicleId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "gross_value")
    public double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(double grossValue) {
        this.grossValue = grossValue;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp data) {
        this.date = data;
    }

    @Basic
    @Column(name = "supplier_id")
    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
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
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Supplier getSupplierBySupplierId() {
        return supplierBySupplierId;
    }

    public void setSupplierBySupplierId(Supplier supplierBySupplierId) {
        this.supplierBySupplierId = supplierBySupplierId;
    }

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Vehicle getVehicleByVehicleId() {
        return vehicleByVehicleId;
    }

    public void setVehicleByVehicleId(Vehicle vehicleByVehicleId) {
        this.vehicleByVehicleId = vehicleByVehicleId;
    }
}
