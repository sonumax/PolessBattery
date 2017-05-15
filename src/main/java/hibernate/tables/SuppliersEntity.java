package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Suppliers", schema = "dbo", catalog = "PolessBattery")
public class SuppliersEntity {
    private int supplierId;
    private String organizationName;
    private String address;
    private String contactPerson;
    private String phone;
    private String mail;
    private ComponentEntity componentId;

    @Id
    @Column(name = "SupplierID")
    @GenericGenerator(name = "supp", strategy = "increment")
    @GeneratedValue(generator = "supp")
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "OrganizationName")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "ContactPerson")
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "E-mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String eMail) {
        this.mail = eMail;
    }

    @ManyToOne
    @JoinColumn(name = "ComponentID", referencedColumnName = "ComponentID", nullable = false)
    public ComponentEntity getComponentId() {
        return componentId;
    }

    public void setComponentId(ComponentEntity componentByComponentId) {
        this.componentId = componentByComponentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuppliersEntity that = (SuppliersEntity) o;

        if (supplierId != that.supplierId) return false;
        if (componentId != that.componentId) return false;
        if (organizationName != null ? !organizationName.equals(that.organizationName) : that.organizationName != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = supplierId;
        result = 31 * result + componentId.getComponentId();
        result = 31 * result + (organizationName != null ? organizationName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}
