package hibernate.tables;

import javax.persistence.*;

@Entity
@Table(name = "Battery_Components", schema = "dbo", catalog = "PolessBattery")
@IdClass(BatteryComponentsEntityPK.class)
public class BatteryComponentsEntity {

    private int batteryId;
    private int componentId;
    private int countComponents;
    private BatteryEntity battery;
    private ComponentEntity component;

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "batteryId", column = @Column(name= "batteryID")),
            @AttributeOverride(name = "componentId", column = @Column(name= "componentId"))
    })
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @ManyToOne
    @JoinColumn(name = "batteryID", referencedColumnName = "BatteryID", updatable = false, insertable=false)
    public BatteryEntity getBattery() {
        return battery;
    }

    public void setBattery(BatteryEntity battery) {
        this.battery = battery;
    }

    @ManyToOne
    @JoinColumn(name = "componentId", referencedColumnName = "ComponentID", updatable = false, insertable=false)
    public ComponentEntity getComponent() {
        return component;
    }

    public void setComponent(ComponentEntity component) {
        this.component = component;
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
