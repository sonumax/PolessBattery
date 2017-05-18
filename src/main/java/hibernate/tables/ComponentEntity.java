package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Component", schema = "dbo", catalog = "PolessBattery")
public class ComponentEntity {
    private int componentId;
    private String nameComponent;
    private Double price;
    private String unit;
    private StorageComponentEntity storageComponentId;
    private Set<SuppliersEntity> suppliers;
    private Set<BatteryComponentsEntity> batteryComponents;

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

    @Basic
    @Column(name = "Unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @OneToOne(mappedBy = "component")
    public StorageComponentEntity getStorageComponentId() {
        return storageComponentId;
    }

    public void setStorageComponentId(StorageComponentEntity storageComponent) {
        this.storageComponentId = storageComponent;
    }

    @OneToMany(mappedBy = "component")
    public Set<SuppliersEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SuppliersEntity> suppliers) {
        this.suppliers = suppliers;
    }

    @OneToMany(mappedBy = "component")
    public Set<BatteryComponentsEntity> getBatteryComponents() {
        return batteryComponents;
    }

    public void setBatteryComponents(Set<BatteryComponentsEntity> batteryComponents) {
        this.batteryComponents = batteryComponents;
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
