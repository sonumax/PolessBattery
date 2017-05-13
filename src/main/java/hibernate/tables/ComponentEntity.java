package hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Component", schema = "dbo", catalog = "PolessBattery")
public class ComponentEntity {
    private int componentId;
    private String nameComponent;
    private StorageComponentEntity storageComponentByComponentId;
    private Set<SuppliersEntity> suppliersByComponentId;

    @Id
    @Column(name = "ComponentID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "NameComponent")
    public String getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
    }

    @OneToOne(mappedBy = "componentByComponentId")
    public StorageComponentEntity getStorageComponentByComponentId() {
        return storageComponentByComponentId;
    }

    public void setStorageComponentByComponentId(StorageComponentEntity storageComponentByComponentId) {
        this.storageComponentByComponentId = storageComponentByComponentId;
    }

    @OneToMany(mappedBy = "componentId")
    public Set<SuppliersEntity> getSuppliersByComponentId() {
        return suppliersByComponentId;
    }

    public void setSuppliersByComponentId(Set<SuppliersEntity> suppliersByComponentId) {
        this.suppliersByComponentId = suppliersByComponentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentEntity that = (ComponentEntity) o;

        if (componentId != that.componentId) return false;
        if (nameComponent != null ? !nameComponent.equals(that.nameComponent) : that.nameComponent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = componentId;
        result = 31 * result + (nameComponent != null ? nameComponent.hashCode() : 0);
        return result;
    }
}
