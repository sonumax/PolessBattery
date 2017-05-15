package hibernate.dao.Interface;

import hibernate.tables.BatteryEntity;

import java.util.Collection;

public interface IBattery {
    void addBattery(final BatteryEntity battery);
    void updateBattery(final BatteryEntity battery);
    void deleteBattery(final BatteryEntity battery);
    BatteryEntity getBatteryById(final int id);
    Collection<BatteryEntity> getByMark(final String mark);
    Collection<BatteryEntity> getByCapacity(final double capacity);
    Collection<BatteryEntity> getByAmperage(final double amperage);
    Collection<BatteryEntity> getByPolarityId(final int polarityId);
    Collection<BatteryEntity> getAllCustomers();
}
