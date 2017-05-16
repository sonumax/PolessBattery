package hibernate.dao.Impl;

import hibernate.dao.Interface.IBatteryComponent;
import hibernate.dbService.DBService;
import hibernate.tables.BatteryComponentsEntity;
import hibernate.tables.BatteryEntity;
import hibernate.tables.ComponentEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class BatteryComponent implements IBatteryComponent<BatteryComponentsEntity> {

    private final DBService<BatteryComponentsEntity> dbService = new DBService<>();

    @Override
    public void add(final BatteryComponentsEntity batteryComponentsEntity) {
        dbService.insertToDB(batteryComponentsEntity);
    }

    @Override
    public void update(final BatteryComponentsEntity batteryComponentsEntity) {
        dbService.updateInDB(batteryComponentsEntity);
    }

    @Override
    public void delete(final BatteryComponentsEntity batteryComponentsEntity) {
        dbService.deleteInDB(batteryComponentsEntity);
    }

    @Override
    public Collection<BatteryComponentsEntity> getAll() {
        Collection<BatteryComponentsEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryComponentsEntity> criteria = builder.createQuery(BatteryComponentsEntity.class);
            Root<BatteryComponentsEntity> resultRoot = criteria.from(BatteryComponentsEntity.class);
            criteria.select(resultRoot);
            return criteria;
        });
        return result;
    }

    @Override
    public BatteryComponentsEntity getByBatteryId(final int id) {
        BatteryComponentsEntity result = dbService.getUniqueResult(builder -> {
            CriteriaQuery<BatteryComponentsEntity> criteria = builder.createQuery(BatteryComponentsEntity.class);
            Root<BatteryComponentsEntity> resultRoot = criteria.from(BatteryComponentsEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("batteryId"), id));
            return criteria;
        });
        return result;
    }

    @Override
    public BatteryComponentsEntity getByComponentId(final int id) {
        BatteryComponentsEntity result = dbService.getUniqueResult(builder -> {
            CriteriaQuery<BatteryComponentsEntity> criteria = builder.createQuery(BatteryComponentsEntity.class);
            Root<BatteryComponentsEntity> resultRoot = criteria.from(BatteryComponentsEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("componentId"), id));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryComponentsEntity> getByComponent(final ComponentEntity component) {
        Collection<BatteryComponentsEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryComponentsEntity> criteria = builder.createQuery(BatteryComponentsEntity.class);
            Root<BatteryComponentsEntity> resultRoot = criteria.from(BatteryComponentsEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("componentByComponentId"), component));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<BatteryComponentsEntity> getByBattery(final BatteryEntity battery) {
        Collection<BatteryComponentsEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<BatteryComponentsEntity> criteria = builder.createQuery(BatteryComponentsEntity.class);
            Root<BatteryComponentsEntity> resultRoot = criteria.from(BatteryComponentsEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("batteryByBatteryId"), battery));
            return criteria;
        });
        return result;
    }
}
