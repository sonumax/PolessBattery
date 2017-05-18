package hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Brigade", schema = "dbo", catalog = "PolessBattery")
public class BrigadeEntity {
    private String brigadeId;
    private int quantityEmployee;
    private Set<EmployeesEntity> employees;
    private Set<ProductionAssemblyEntity> productionAssemblies;
    private Set<ProductionPackagingEntity> productionPackagings;

    @Id
    @Column(name = "BrigadeID")
    public String getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(String brigadeId) {
        this.brigadeId = brigadeId;
    }

    @Basic
    @Column(name = "QuantityEmployee")
    public int getQuantityEmployee() {
        return quantityEmployee;
    }

    public void setQuantityEmployee(int quantityEmployee) {
        this.quantityEmployee = quantityEmployee;
    }

    @OneToMany(mappedBy = "brigade")
    public Set<EmployeesEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeesEntity> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "brigade")
    public Set<ProductionAssemblyEntity> getProductionAssemblies() {
        return productionAssemblies;
    }

    public void setProductionAssemblies(Set<ProductionAssemblyEntity> productionAssemblies) {
        this.productionAssemblies = productionAssemblies;
    }

    @OneToMany(mappedBy = "brigade")
    public Set<ProductionPackagingEntity> getProductionPackagings() {
        return productionPackagings;
    }

    public void setProductionPackagings(Set<ProductionPackagingEntity> productionPackagings) {
        this.productionPackagings = productionPackagings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrigadeEntity that = (BrigadeEntity) o;

        if (quantityEmployee != that.quantityEmployee) return false;
        if (brigadeId != null ? !brigadeId.equals(that.brigadeId) : that.brigadeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brigadeId != null ? brigadeId.hashCode() : 0;
        result = 31 * result + quantityEmployee;
        return result;
    }
}
