package backend.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Supplier {
    private long id;
    private String name;
    private Collection<Fuelling> fuellingsById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "supplierBySupplierId")
    public Collection<Fuelling> getFuellingsById() {
        return fuellingsById;
    }

    public void setFuellingsById(Collection<Fuelling> fuellingsById) {
        this.fuellingsById = fuellingsById;
    }
}
