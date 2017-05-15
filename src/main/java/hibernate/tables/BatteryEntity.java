package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Battery", schema = "dbo", catalog = "PolessBattery")
public class BatteryEntity {
    private int batteryId;
    private String mark;
    private double capacity;
    private double amperage;
    private PolarityEntity polarityId;
    private StorageFinishedProductsEntity storageFinishedProductsByBatteryId;
    private Set<OrdersEntity> ordersByBatteryId;
    private Set<ProductionAssemblyEntity> productionAssembliesByBatteryId;
    private Set<BatteryComponentsEntity> batteryComponentsByBatteryId;

    @Id
    @Column(name = "BatteryID")
    @GenericGenerator(name = "batt", strategy = "increment")
    @GeneratedValue(generator = "batt")
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    @Basic
    @Column(name = "Mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "Capacity")
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "Amperage")
    public double getAmperage() {
        return amperage;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }

    @ManyToOne
    @JoinColumn(name = "PolarityID", referencedColumnName = "PolarityID", nullable = false)
    public PolarityEntity getPolarityId() {
        return polarityId;
    }

    public void setPolarityId(PolarityEntity polarityByPolarityId) {
        this.polarityId = polarityByPolarityId;
    }

    @OneToMany(mappedBy = "batteryId")
    public Set<OrdersEntity> getOrdersByBatteryId() {
        return ordersByBatteryId;
    }

    public void setOrdersByBatteryId(Set<OrdersEntity> ordersByBatteryId) {
        this.ordersByBatteryId = ordersByBatteryId;
    }

    @OneToMany(mappedBy = "batteryByBatteryId")
    public Set<ProductionAssemblyEntity> getProductionAssembliesByBatteryId() {
        return productionAssembliesByBatteryId;
    }

    public void setProductionAssembliesByBatteryId(Set<ProductionAssemblyEntity> productionAssembliesByBatteryId) {
        this.productionAssembliesByBatteryId = productionAssembliesByBatteryId;
    }

    @OneToOne(mappedBy = "batteryByBatteryId")
    public StorageFinishedProductsEntity getStorageFinishedProductsByBatteryId() {
        return storageFinishedProductsByBatteryId;
    }

    public void setStorageFinishedProductsByBatteryId(StorageFinishedProductsEntity storageFinishedProductsByBatteryId) {
        this.storageFinishedProductsByBatteryId = storageFinishedProductsByBatteryId;
    }

    @OneToMany(mappedBy = "batteryByBatteryId")
    public Set<BatteryComponentsEntity> getBatteryComponentsByBatteryId() {
        return batteryComponentsByBatteryId;
    }

    public void setBatteryComponentsByBatteryId(Set<BatteryComponentsEntity> batteryComponentsByBatteryId) {
        this.batteryComponentsByBatteryId = batteryComponentsByBatteryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryEntity that = (BatteryEntity) o;

        if (batteryId != that.batteryId) return false;
        if (Double.compare(that.capacity, capacity) != 0) return false;
        if (Double.compare(that.amperage, amperage) != 0) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = batteryId;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        temp = Double.doubleToLongBits(capacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amperage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
