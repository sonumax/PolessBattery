package hibernate.tables;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class BatteryComponentsEntityPK implements Serializable {
    private int batteryId;
    private int componentId;

    @Id
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    @Id
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryComponentsEntityPK that = (BatteryComponentsEntityPK) o;

        if (batteryId != that.batteryId) return false;
        if (componentId != that.componentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = batteryId;
        result = 31 * result + componentId;
        return result;
    }
}
