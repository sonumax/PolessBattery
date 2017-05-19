package hibernate.dao.Impl;

import hibernate.dao.Interface.Organization;
import hibernate.dbService.DBService;
import hibernate.tables.CustomersEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class CustomerDAO implements Organization<CustomersEntity> {

    private final DBService<CustomersEntity> dbService = new DBService<>();

    @Override
    public void add(final CustomersEntity customer) {
       dbService.insertToDB(customer);
    }

    @Override
    public void update(final CustomersEntity customer) {
        dbService.updateInDB(customer);
    }

    @Override
    public void delete(final CustomersEntity customer) {
        dbService.deleteInDB(customer);
    }

    @Override
    public CustomersEntity getByID(final int id) {
        return dbService.getByID(CustomersEntity.class, id);
    }

    @Override
    public CustomersEntity getOrdersByOrganizationName(final String organizationName) {
        CustomersEntity customer = dbService.getUniqueResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            criteria.where(builder.equal(customerRoot.get("organizationName"), organizationName));
            return criteria;
        });
        return customer;
    }

    @Override
    public CustomersEntity getOrdersByAddress(final String address) {
        CustomersEntity customer = dbService.getUniqueResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            criteria.where(builder.equal(customerRoot.get("address"), address));
            return criteria;
        });
        return customer;
    }

    @Override
    public CustomersEntity getOrdersByContactPerson(final String contactPerson) {
        CustomersEntity customer = dbService.getUniqueResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            criteria.where(builder.equal(customerRoot.get("contactPerson"), contactPerson));
            return criteria;
        });
        return customer;
    }

    @Override
    public CustomersEntity getOrdersByPhone(final String phone) {
        CustomersEntity customer = dbService.getUniqueResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            criteria.where(builder.equal(customerRoot.get("phone"), phone));
            return criteria;
        });
        return customer;
    }

    @Override
    public CustomersEntity getOrdersByEMail(final String eMail) {
        CustomersEntity customer = dbService.getUniqueResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            criteria.where(builder.equal(customerRoot.get("eMail"), eMail));
            return criteria;
        });
        return customer;
    }

    @Override
    public Collection<CustomersEntity> getAll() {
        Collection<CustomersEntity> customers = dbService.getCollectionResult(builder -> {
            CriteriaQuery<CustomersEntity> criteria = builder.createQuery(CustomersEntity.class);
            Root<CustomersEntity> customerRoot = criteria.from(CustomersEntity.class);
            criteria.select(customerRoot);
            return criteria;
        });
        return customers;
    }
}
