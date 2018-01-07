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

    @OneToMany(mappedBy = "driverByDriverId")
    public Collection<Trip> getTripsById() {
        return tripsById;
    }

    public void setTripsById(Collection<Trip> tripsById) {
        this.tripsById = tripsById;
    }
}
