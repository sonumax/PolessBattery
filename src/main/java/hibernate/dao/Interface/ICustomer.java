package hibernate.dao.Interface;

import hibernate.tables.CustomersEntity;

import java.util.Collection;
import java.util.Date;

public interface ICustomer {
    void addCustomer(final CustomersEntity customer);
    void updateCustomer(final CustomersEntity customer);
    void deleteCustomer(final CustomersEntity customer);
    CustomersEntity getCustomerById(final int id);
    CustomersEntity getOrdersByOrganizationName(final String organizationName);
    CustomersEntity getOrdersByAddress(final String address);
    CustomersEntity getOrdersByContactPerson(final String contactPerson);
    CustomersEntity getOrdersByPhone(final String phone);
    CustomersEntity getOrdersByEMail(final String eMail);
    Collection<CustomersEntity> getAllCustomers();
}
