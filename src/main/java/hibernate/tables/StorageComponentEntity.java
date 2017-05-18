package hibernate.tables;

import javax.persistence.*;

@Entity
@Table(name = "StorageComponent", schema = "dbo", catalog = "PolessBattery")
public class StorageComponentEntity {
    private int storageComponentId;
    private double quantityProduct;
    private String unit;
    private ComponentEntity component;

    @Id
    @Column(name = "ComponentID")
    public int getStorageComponentId() {
        return storageComponentId;
    }

    public void setStorageComponentId(int componentId) {
        this.storageComponentId = componentId;
    }

    @Basic
    @Column(name = "QuantityProduct")
    public double getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(double quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    @Basic
    @Column(name = "Unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @OneToOne
    @JoinColumn(name = "ComponentID", referencedColumnName = "ComponentID", nullable = false)
    public ComponentEntity getComponent() {
        return component;
    }

    public void setComponent(ComponentEntity component) {
        this.component = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageComponentEntity that = (StorageComponentEntity) o;

        if (storageComponentId != that.storageComponentId) return false;
        if (Double.compare(that.quantityProduct, quantityProduct) != 0) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = storageComponentId;
        temp = Double.doubleToLongBits(quantityProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
