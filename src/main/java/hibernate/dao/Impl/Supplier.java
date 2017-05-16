package hibernate.dao.Impl;

import hibernate.dao.Interface.Organization;
import hibernate.dbService.DBService;
import hibernate.tables.EmployeesEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class Supplier implements Organization<EmployeesEntity> {

    private final DBService<EmployeesEntity> dbService = new DBService<>();

    @Override
    public void add(final EmployeesEntity employeesEntity) {
        dbService.insertToDB(employeesEntity);
    }

    @Override
    public void update(final EmployeesEntity employeesEntity) {
        dbService.updateInDB(employeesEntity);
    }

    @Override
    public void delete(final EmployeesEntity employeesEntity) {
        dbService.deleteInDB(employeesEntity);
    }

    @Override
    public EmployeesEntity getByID(final int id) {
        return dbService.getByID(EmployeesEntity.class, id);
    }

    @Override
    public EmployeesEntity getOrdersByOrganizationName(final String organizationName) {
        EmployeesEntity employee = dbService.getUniqueResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("organizationName"), organizationName));
            return criteria;
        });
        return employee;
    }

    @Override
    public EmployeesEntity getOrdersByAddress(final String address) {
        EmployeesEntity employee = dbService.getUniqueResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("address"), address));
            return criteria;
        });
        return employee;
    }

    @Override
    public EmployeesEntity getOrdersByContactPerson(final String contactPerson) {
        EmployeesEntity employee = dbService.getUniqueResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("contactPerson"), contactPerson));
            return criteria;
        });
        return employee;
    }

    @Override
    public EmployeesEntity getOrdersByPhone(final String phone) {
        EmployeesEntity employee = dbService.getUniqueResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("phone"), phone));
            return criteria;
        });
        return employee;
    }

    @Override
    public EmployeesEntity getOrdersByEMail(final String eMail) {
        EmployeesEntity employee = dbService.getUniqueResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("eMail"), eMail));
            return criteria;
        });
        return employee;
    }

    @Override
    public Collection<EmployeesEntity> getAll() {
        Collection<EmployeesEntity> employee = dbService.getCollectionResult(builder -> {
            CriteriaQuery<EmployeesEntity> criteria = builder.createQuery(EmployeesEntity.class);
            Root<EmployeesEntity> root = criteria.from(EmployeesEntity.class);
            criteria.select(root);
            return criteria;
        });
        return employee;
    }
}
