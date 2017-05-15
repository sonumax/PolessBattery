package hibernate.tables;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Battery_Components", schema = "dbo", catalog = "PolessBattery")
public class BatteryComponentsEntity  implements Serializable {
    private int batteryId;
    private int componentId;
    private int countComponents;
    private BatteryEntity batteryByBatteryId;
    private ComponentEntity componentByComponentId;

    @Id
    @Column(name = "BatteryID")
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    @Id
    @Column(name = "ComponentId")
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @ManyToOne
    @JoinColumn(name = "BatteryID", referencedColumnName = "BatteryID", nullable = false)
    public BatteryEntity getBatteryByBatteryId() {
        return batteryByBatteryId;
    }

    public void setBatteryByBatteryId(BatteryEntity batteryByBatteryId) {
        this.batteryByBatteryId = batteryByBatteryId;
    }

    @ManyToOne
    @JoinColumn(name = "ComponentId", referencedColumnName = "ComponentID", nullable = false)
    public ComponentEntity getComponentByComponentId() {
        return componentByComponentId;
    }

    public void setComponentByComponentId(ComponentEntity componentByComponentId) {
        this.componentByComponentId = componentByComponentId;
    }

    @Basic
    @Column(name = "CountComponents")
    public int getCountComponents() {
        return countComponents;
    }

    public void setCountComponents(int countComponents) {
        this.countComponents = countComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryComponentsEntity that = (BatteryComponentsEntity) o;

        if (batteryId != that.batteryId) return false;
        if (componentId != that.componentId) return false;
        if (countComponents != that.countComponents) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = batteryId;
        result = 31 * result + componentId;
        result = 31 * result + countComponents;
        return result;
    }
}