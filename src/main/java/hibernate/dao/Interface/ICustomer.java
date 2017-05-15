package hibernate.dao.Interface;

import java.util.Collection;

public interface ICustomer<T> extends BaseCommand<T> {
    T getCustomerById(final int id);
    T getOrdersByOrganizationName(final String organizationName);
    T getOrdersByAddress(final String address);
    T getOrdersByContactPerson(final String contactPerson);
    T getOrdersByPhone(final String phone);
    T getOrdersByEMail(final String eMail);
    Collection<T> getAllCustomers();
}
