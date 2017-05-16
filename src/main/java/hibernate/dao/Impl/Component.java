package hibernate.dao.Impl;

import hibernate.dao.Interface.IComponent;
import hibernate.dbService.DBService;
import hibernate.tables.ComponentEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class Component implements IComponent<ComponentEntity>{

    private final DBService<ComponentEntity> dbService = new DBService<>();

    @Override
    public void add(final ComponentEntity componentEntity) {
        dbService.insertToDB(componentEntity);
    }

    @Override
    public void update(final ComponentEntity componentEntity) {
        dbService.updateInDB(componentEntity);
    }

    @Override
    public void delete(final ComponentEntity componentEntity) {
        dbService.deleteInDB(componentEntity);
    }

    @Override
    public Collection<ComponentEntity> getAll() {
        Collection<ComponentEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<ComponentEntity> criteria = builder.createQuery(ComponentEntity.class);
            Root<ComponentEntity> resultRoot = criteria.from(ComponentEntity.class);
            criteria.select(resultRoot);
            return criteria;
        });
        return result;
    }

    @Override
    public ComponentEntity getComponentById(final int id) {
        return dbService.getByID(ComponentEntity.class, id);
    }

    @Override
    public ComponentEntity getComponentByName(final String name) {
        ComponentEntity result = dbService.getUniqueResult(builder -> {
            CriteriaQuery<ComponentEntity> criteria = builder.createQuery(ComponentEntity.class);
            Root<ComponentEntity> resultRoot = criteria.from(ComponentEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("nameComponent"), name));
            return criteria;
        });
        return result;
    }

    @Override
    public Collection<ComponentEntity> getComponentsByPrice(final double price) {
        Collection<ComponentEntity> result = dbService.getCollectionResult(builder -> {
            CriteriaQuery<ComponentEntity> criteria = builder.createQuery(ComponentEntity.class);
            Root<ComponentEntity> resultRoot = criteria.from(ComponentEntity.class);
            criteria.select(resultRoot);
            criteria.where(builder.equal(resultRoot.get("price"), price));
            return criteria;
        });
        return result;
    }
}
