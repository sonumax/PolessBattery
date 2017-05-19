package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Battery", schema = "dbo", catalog = "PolessBattery")
public class BatteryEntity {
    private int batteryId;
    private String mark;
    private int capacity;
    private int amperage;
    private PolarityEntity polarity;
    private StorageFinishedProductsEntity storageFinishedProducts;
    private Set<OrdersEntity> orders;
    private Set<ProductionAssemblyEntity> productionAssemblies;
    private Set<BatteryComponentsEntity> batteryComponents;

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
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "Amperage")
    public int getAmperage() {
        return amperage;
    }

    public void setAmperage(int amperage) {
        this.amperage = amperage;
    }

    @ManyToOne
    @JoinColumn(name = "PolarityID", referencedColumnName = "PolarityID", nullable = false)
    public PolarityEntity getPolarity() {
        return polarity;
    }

    public void setPolarity(PolarityEntity polarity) {
        this.polarity = polarity;
    }

    @OneToMany(mappedBy = "battery")
    public Set<OrdersEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrdersEntity> ordersByBatteryId) {
        this.orders = ordersByBatteryId;
    }

    @OneToMany(mappedBy = "battery")
    public Set<ProductionAssemblyEntity> getProductionAssemblies() {
        return productionAssemblies;
    }

    public void setProductionAssemblies(Set<ProductionAssemblyEntity> productionAssemblies) {
        this.productionAssemblies = productionAssemblies;
    }

    @OneToOne(mappedBy = "battery")
    public StorageFinishedProductsEntity getStorageFinishedProducts() {
        return storageFinishedProducts;
    }

    public void setStorageFinishedProducts(StorageFinishedProductsEntity storageFinishedProducts) {
        this.storageFinishedProducts = storageFinishedProducts;
    }

    @OneToMany(mappedBy = "battery", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public double countPrice() {
        double priceBattery = 0;
        int countComponent;
        double priceComponent;
        ComponentEntity component;
        for (BatteryComponentsEntity batteryComponents : batteryComponents) {
            countComponent = batteryComponents.getCountComponents();
            component = batteryComponents.getComponent();
            priceComponent = component.getPrice();
            priceBattery += priceComponent * countComponent;
        }
        return priceBattery;
    }
}
