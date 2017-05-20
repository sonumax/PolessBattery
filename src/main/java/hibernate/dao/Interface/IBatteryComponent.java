package hibernate.dao.Interface;

import hibernate.tables.BatteryEntity;
import hibernate.tables.ComponentEntity;

import java.util.Collection;

public interface IBatteryComponent<T> extends BaseCommand<T> {
    T getByBatteryId(final int id);
    T getByComponentId(final int id);
    Collection<T> getByComponent(final ComponentEntity component);
    Collection<T> getByBattery(final BatteryEntity battery);
}
