package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Component", schema = "dbo", catalog = "PolessBattery")
public class ComponentEntity {
    private int componentId;
    private String nameComponent;
    private StorageComponentEntity storageComponentByComponentId;
    private Double price;
    private Set<SuppliersEntity> suppliersByComponentId;
    private Set<BatteryComponentsEntity> batteryComponentsByComponentId;


    @Id
    @Column(name = "ComponentID")
    @GenericGenerator(name = "comp", strategy = "increment")
    @GeneratedValue(generator = "comp")
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

    @Basic
    @Column(name = "Price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    @OneToMany(mappedBy = "componentByComponentId")
    public Set<BatteryComponentsEntity> getBatteryComponentsByComponentId() {
        return batteryComponentsByComponentId;
    }

    public void setBatteryComponentsByComponentId(Set<BatteryComponentsEntity> batteryComponentsByComponentId) {
        this.batteryComponentsByComponentId = batteryComponentsByComponentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentEntity that = (ComponentEntity) o;

        if (componentId != that.componentId) return false;
        if (nameComponent != null ? !nameComponent.equals(that.nameComponent) : that.nameComponent != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = componentId;
        result = 31 * result + (nameComponent != null ? nameComponent.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
