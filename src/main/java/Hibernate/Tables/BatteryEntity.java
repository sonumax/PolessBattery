package Hibernate.Tables;

import javax.persistence.*;

@Entity
@Table(name = "Battery", schema = "dbo", catalog = "PolessBattery")
public class BatteryEntity {
    private int batteryId;
    private String mark;
    private double capacity;
    private double amperage;

    @Id
    @Column(name = "BatteryID", nullable = false)
    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    @Basic
    @Column(name = "Mark", nullable = false, length = 20)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "Capacity", nullable = false, precision = 0)
    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "Amperage", nullable = false, precision = 0)
    public double getAmperage() {
        return amperage;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
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
