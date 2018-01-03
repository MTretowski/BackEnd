package backend.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Driver {
    private long id;
    private String firstName;
    private String lastName;
    private boolean active;
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
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "driverByFirstDriverId")
    public Collection<Trip> getTripsById() {
        return tripsById;
    }

    public void setTripsById(Collection<Trip> tripsById) {
        this.tripsById = tripsById;
    }

    @OneToMany(mappedBy = "driverBySecondDriverId")
    public Collection<Trip> getTripsById_0() {
        return tripsById_0;
    }

    public void setTripsById_0(Collection<Trip> tripsById_0) {
        this.tripsById_0 = tripsById_0;
    }
}
