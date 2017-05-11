package Hibernate.Tables;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ProductionAssembly", schema = "dbo", catalog = "PolessBattery")
public class ProductionAssemblyEntity {
    private int planAssemblyId;
    private double quantityFinishProduct;
    private Date dateAssembly;

    @Id
    @Column(name = "PlanAssemblyID", nullable = false)
    public int getPlanAssemblyId() {
        return planAssemblyId;
    }

    public void setPlanAssemblyId(int planAssemblyId) {
        this.planAssemblyId = planAssemblyId;
    }

    @Basic
    @Column(name = "QuantityFinishProduct", nullable = false, precision = 0)
    public double getQuantityFinishProduct() {
        return quantityFinishProduct;
    }

    public void setQuantityFinishProduct(double quantityFinishProduct) {
        this.quantityFinishProduct = quantityFinishProduct;
    }

    @Basic
    @Column(name = "DateAssembly", nullable = false)
    public Date getDateAssembly() {
        return dateAssembly;
    }

    public void setDateAssembly(Date dateAssembly) {
        this.dateAssembly = dateAssembly;
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
