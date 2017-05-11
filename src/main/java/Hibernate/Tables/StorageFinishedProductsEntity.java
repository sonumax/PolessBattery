package Hibernate.Tables;

import javax.persistence.*;

@Entity
@Table(name = "StorageFinishedProducts", schema = "dbo", catalog = "PolessBattery")
public class StorageFinishedProductsEntity {
    private int batteryId;
    private double quantityProduct;

    @Id
    @Column(name = "BatteryID", nullable = false)
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    @Basic
    @Column(name = "QuantityProduct", nullable = false, precision = 0)
    public double getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(double quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageFinishedProductsEntity that = (StorageFinishedProductsEntity) o;

        if (batteryId != that.batteryId) return false;
        if (Double.compare(that.quantityProduct, quantityProduct) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = batteryId;
        temp = Double.doubleToLongBits(quantityProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
