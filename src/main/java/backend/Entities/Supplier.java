package backend.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Supplier {
    private long id;
    private String name;
    private Collection<Fuelling> fuellingsById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != supplier.id) return false;
        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "supplierBySupplierId")
    public Collection<Fuelling> getFuellingsById() {
        return fuellingsById;
    }

    public void setFuellingsById(Collection<Fuelling> fuellingsById) {
        this.fuellingsById = fuellingsById;
    }
}
