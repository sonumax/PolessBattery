package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Employees", schema = "dbo", catalog = "PolessBattery")
public class EmployeesEntity {
    private int employeeId;
    private String surname;
    private String name;
    private String patronymic;
    private String specialty;
    private String rank;
    private String address;
    private String phone;
    private String eMail;
    private Date dateRecruitment;
    private BrigadeEntity brigadeByBrigadeId;

    @Id
    @Column(name = "EmployeeID")
    @GenericGenerator(name = "empl", strategy = "increment")
    @GeneratedValue(generator = "empl")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "Surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "Specialty")
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Basic
    @Column(name = "Rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "E-mail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "DateRecruitment")
    public Date getDateRecruitment() {
        return dateRecruitment;
    }

    public void setDateRecruitment(Date dateRecruitment) {
        this.dateRecruitment = dateRecruitment;
    }


    @ManyToOne
    @JoinColumn(name = "BrigadeID", referencedColumnName = "BrigadeID", nullable = false)
    public BrigadeEntity getBrigadeByBrigadeId() {
        return brigadeByBrigadeId;
    }

    public void setBrigadeByBrigadeId(BrigadeEntity brigadeByBrigadeId) {
        this.brigadeByBrigadeId = brigadeByBrigadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeesEntity that = (EmployeesEntity) o;

        if (employeeId != that.employeeId) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (specialty != null ? !specialty.equals(that.specialty) : that.specialty != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (dateRecruitment != null ? !dateRecruitment.equals(that.dateRecruitment) : that.dateRecruitment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (dateRecruitment != null ? dateRecruitment.hashCode() : 0);
        return result;
    }
}
