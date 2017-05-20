package hibernate.tables;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ProductionPackaging", schema = "dbo", catalog = "PolessBattery")
public class ProductionPackagingEntity {
    private int planPackagingId;
    private double quantityFinishProduct;
    private Date dateAssembly;
    private ProductionAssemblyEntity productionAssembly;
    private BrigadeEntity brigade;

    @Id
    @Column(name = "PlanPackagingID")
    @GenericGenerator(name = "productPack", strategy = "increment")
    @GeneratedValue(generator = "productPack")
    public int getPlanPackagingId() {
        return planPackagingId;
    }

    public void setPlanPackagingId(int planPackagingId) {
        this.planPackagingId = planPackagingId;
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
    @JoinColumn(name = "PlanAssemblyID", referencedColumnName = "PlanAssemblyID", nullable = false)
    public ProductionAssemblyEntity getProductionAssembly() {
        return productionAssembly;
    }

    public void setProductionAssembly(ProductionAssemblyEntity productionAssembly) {
        this.productionAssembly = productionAssembly;
    }

    @ManyToOne
    @JoinColumn(name = "BrigadeID", referencedColumnName = "BrigadeID", nullable = false)
    public BrigadeEntity getBrigade() {
        return brigade;
    }

    public void setBrigade(BrigadeEntity brigade) {
        this.brigade = brigade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductionPackagingEntity that = (ProductionPackagingEntity) o;

        if (planPackagingId != that.planPackagingId) return false;
        if (Double.compare(that.quantityFinishProduct, quantityFinishProduct) != 0) return false;
        if (dateAssembly != null ? !dateAssembly.equals(that.dateAssembly) : that.dateAssembly != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = planPackagingId;
        temp = Double.doubleToLongBits(quantityFinishProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateAssembly != null ? dateAssembly.hashCode() : 0);
        return result;
    }
}
