package Hibernate.Tables;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Orders", schema = "dbo", catalog = "PolessBattery")
public class OrdersEntity {
    private int orderId;
    private double quantityProduct;
    private Date dateExecution;

    @Id
    @Column(name = "OrderID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "QuantityProduct", nullable = false, precision = 0)
    public double getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(double quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    @Basic
    @Column(name = "DateExecution", nullable = true)
    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderId != that.orderId) return false;
        if (Double.compare(that.quantityProduct, quantityProduct) != 0) return false;
        if (dateExecution != null ? !dateExecution.equals(that.dateExecution) : that.dateExecution != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        temp = Double.doubleToLongBits(quantityProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateExecution != null ? dateExecution.hashCode() : 0);
        return result;
    }
}
