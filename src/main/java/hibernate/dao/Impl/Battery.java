package hibernate.dao.Impl;

import hibernate.dao.Interface.IBattery;
import hibernate.dbService.DBService;
import hibernate.tables.BatteryEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class Battery implements IBattery<BatteryEntity> {

    private final DBService<BatteryEntity> dbService = new DBService<>();

    @Override
    public void add(final BatteryEntity battery) {
        dbService.insertToDB(battery);
    }

    @Override
    public void update(final BatteryEntity battery) {
        dbService.updateInDB(battery);
    }

    @Override
    public void delete(final BatteryEntity battery) {
        dbService.deleteInDB(battery);
    }

    @Override
    public BatteryEntity getBatteryById(final int id) {
        return dbService.getByID(BatteryEntity.class, id);
    }

    @Override
    public Collection<BatteryEntity> getByMark(final String mark) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("mark"), mark));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryEntity> getByCapacity(final double capacity) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("capacity"), capacity));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryEntity> getByAmperage(final double amperage) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("amperage"), amperage));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryEntity> getByPolarityId(final int polarityId) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("polarityId"), polarityId));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryEntity> getAll() {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            return criteria;
        });
        return result;
    }
}
