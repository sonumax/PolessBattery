package Hibernate.Tables;

import javax.persistence.*;

@Entity
@Table(name = "StorageComponent", schema = "dbo", catalog = "PolessBattery")
public class StorageComponentEntity {
    private int componentId;
    private double quantityProduct;
    private String unit;

    @Id
    @Column(name = "ComponentID", nullable = false)
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Basic
    @Column(name = "QuantityProduct", nullable = false, precision = 0)
    public double getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(double quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    @Basic
    @Column(name = "Unit", nullable = false, length = 10)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageComponentEntity that = (StorageComponentEntity) o;

        if (componentId != that.componentId) return false;
        if (Double.compare(that.quantityProduct, quantityProduct) != 0) return false;
        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = componentId;
        temp = Double.doubleToLongBits(quantityProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
