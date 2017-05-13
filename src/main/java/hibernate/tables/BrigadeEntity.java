package hibernate.tables;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Brigade", schema = "dbo", catalog = "PolessBattery")
public class BrigadeEntity {
    private String brigadeId;
    private int quantityEmployee;
    private Set<EmployeesEntity> employeesByBrigadeId;
    private Set<ProductionAssemblyEntity> productionAssembliesByBrigadeId;
    private Set<ProductionPackagingEntity> productionPackagingsByBrigadeId;

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

    @OneToMany(mappedBy = "brigadeByBrigadeId")
    public Set<EmployeesEntity> getEmployeesByBrigadeId() {
        return employeesByBrigadeId;
    }

    public void setEmployeesByBrigadeId(Set<EmployeesEntity> employeesByBrigadeId) {
        this.employeesByBrigadeId = employeesByBrigadeId;
    }

    @OneToMany(mappedBy = "brigadeByBrigadeId")
    public Set<ProductionAssemblyEntity> getProductionAssembliesByBrigadeId() {
        return productionAssembliesByBrigadeId;
    }

    public void setProductionAssembliesByBrigadeId(Set<ProductionAssemblyEntity> productionAssembliesByBrigadeId) {
        this.productionAssembliesByBrigadeId = productionAssembliesByBrigadeId;
    }

    @OneToMany(mappedBy = "brigadeByBrigadeId")
    public Set<ProductionPackagingEntity> getProductionPackagingsByBrigadeId() {
        return productionPackagingsByBrigadeId;
    }

    public void setProductionPackagingsByBrigadeId(Set<ProductionPackagingEntity> productionPackagingsByBrigadeId) {
        this.productionPackagingsByBrigadeId = productionPackagingsByBrigadeId;
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
