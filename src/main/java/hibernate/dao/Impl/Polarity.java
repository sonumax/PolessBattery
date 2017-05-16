package hibernate.dao.Impl;

import hibernate.dao.Interface.IPolarity;
import hibernate.dbService.DBService;
import hibernate.tables.PolarityEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class Polarity implements IPolarity<PolarityEntity> {

    private final DBService<PolarityEntity> dbService = new DBService<>();

    @Override
    public void add(final PolarityEntity polarityEntity) {
        dbService.insertToDB(polarityEntity);
    }

    @Override
    public void update(final PolarityEntity polarityEntity) {
        dbService.updateInDB(polarityEntity);
    }

    @Override
    public void delete(final PolarityEntity polarityEntity) {
        dbService.deleteInDB(polarityEntity);
    }

    @Override
    public PolarityEntity getById(int id) {
        return dbService.getByID(PolarityEntity.class, id);
    }

    @Override
    public PolarityEntity getByName(final String name) {
        PolarityEntity result = dbService.getUniqueResult(builder -> {
            CriteriaQuery<PolarityEntity> criteria = builder.createQuery(PolarityEntity.class);
            Root<PolarityEntity> resultRoot = criteria.from(PolarityEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("namePolarity"), name));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<PolarityEntity> getAll() {
        Collection<PolarityEntity> ordersList = dbService.getCollectionResult(builder -> {
            CriteriaQuery<PolarityEntity> criteria = builder.createQuery(PolarityEntity.class);
            Root<PolarityEntity> ordersRoot = criteria.from(PolarityEntity.class);
            criteria.select(ordersRoot);
            return criteria;
        });
        return ordersList;
    }
}
