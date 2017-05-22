package hibernate.dao.Impl;

import hibernate.dao.Interface.IBattery;
import hibernate.dbService.DBService;
import hibernate.tables.BatteryEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class BatteryDAO implements IBattery<BatteryEntity> {

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
    public Collection<BatteryEntity> getByCapacity(final int capacity) {
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
    public Collection<BatteryEntity> getByAmperage(final int amperage) {
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
            criteria.where(builder.equal(resultRoot.get("polarity"), polarityId));
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

    public Collection<BatteryEntity> getByMarkCapacity(final String mark, final int capacity) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            Predicate predicate = builder.and(builder.equal(resultRoot.get("mark"), mark),
                    builder.equal(resultRoot.get("capacity"), capacity));
            criteria.where(predicate);
            return criteria;
        });
        return result;
    }

    public Collection<BatteryEntity> getByMarkCapacityAmperage(final String mark, final int capacity, final int amperage) {
        Collection<BatteryEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);
            Predicate predicate = builder.and(builder.equal(resultRoot.get("mark"), mark),
                    builder.equal(resultRoot.get("capacity"), capacity),
                    builder.equal(resultRoot.get("amperage"), amperage));
            criteria.where(predicate);
            return criteria;
        });
        return result;
    }

    public BatteryEntity getByMarkCapacityAmperagePolarity (final String mark, final int capacity, final int amperage, final int polarityId) {
        BatteryEntity result = dbService.getUniqueResult(builder -> {
            CriteriaQuery<BatteryEntity> criteria = builder.createQuery(BatteryEntity.class);
            Root<BatteryEntity> resultRoot = criteria.from(BatteryEntity.class);
            criteria.select(resultRoot);

            Predicate predicate = builder.and(builder.equal(resultRoot.get("mark"), mark),
                    builder.equal(resultRoot.get("capacity"), capacity),
                    builder.equal(resultRoot.get("amperage"), amperage),
                    builder.equal(resultRoot.get("polarity"), polarityId));
            criteria.where(predicate);
            return criteria;
        });
        return result;
    }
}
