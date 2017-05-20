package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "ProductionAssembly", schema = "dbo", catalog = "PolessBattery")
public class ProductionAssemblyEntity {
    private int planAssemblyId;
    private double quantityFinishProduct;
    private Date dateAssembly;
    private BatteryEntity battery;
    private BrigadeEntity brigade;
    private Set<ProductionPackagingEntity> productionPackagings;

    @Id
    @Column(name = "PlanAssemblyID")
    @GenericGenerator(name = "productAssem", strategy = "increment")
    @GeneratedValue(generator = "productAssem")
    public int getPlanAssemblyId() {
        return planAssemblyId;
    }

    public void setPlanAssemblyId(int planAssemblyId) {
        this.planAssemblyId = planAssemblyId;
    }

    @Basic
    @Column(name = "QuantityFinishProduct")
    public double getQuantityFinishProduct() {
        return quantityFinishProduct;
    }

    public void setQuantityFinishProduct(double quantityFinishProduct) {
        this.quantityFinishProduct = quantityFinishProduct;
    }

    @Basic
    @Column(name = "DateAssembly")
    public Date getDateAssembly() {
        return dateAssembly;
    }

    public void setDateAssembly(Date dateAssembly) {
        this.dateAssembly = dateAssembly;
    }

    @ManyToOne
    @JoinColumn(name = "BatteryID", referencedColumnName = "BatteryID", nullable = false)
    public BatteryEntity getBattery() {
        return battery;
    }

    public void setBattery(BatteryEntity battery) {
        this.battery = battery;
    }

    @ManyToOne
    @JoinColumn(name = "BrigadeID", referencedColumnName = "BrigadeID", nullable = false)
    public BrigadeEntity getBrigade() {
        return brigade;
    }

    public void setBrigade(BrigadeEntity brigade) {
        this.brigade = brigade;
    }

    @OneToMany(mappedBy = "productionAssembly")
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

        ProductionAssemblyEntity that = (ProductionAssemblyEntity) o;

        if (planAssemblyId != that.planAssemblyId) return false;
        if (Double.compare(that.quantityFinishProduct, quantityFinishProduct) != 0) return false;
        if (dateAssembly != null ? !dateAssembly.equals(that.dateAssembly) : that.dateAssembly != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = planAssemblyId;
        temp = Double.doubleToLongBits(quantityFinishProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateAssembly != null ? dateAssembly.hashCode() : 0);
        return result;
    }
}
