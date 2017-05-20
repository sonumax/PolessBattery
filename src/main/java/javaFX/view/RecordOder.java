package javaFX.view;

import java.sql.Date;

public class RecordOder {

    private final int orderId;
    private final int batteryId;
    private final String organizationName;
    private final String mark;
    private final int capacity;
    private final int amperage;
    private final String namePolarity;
    private final int quantityProduct;
    private final Date dateExecution;

    public RecordOder(final int orderId,final int batteryId,final String organizationName,final String mark,final int capacity,
                      final int amperage,final String namePolarity,final int quantityProduct,final Date dateExecution) {
        this.orderId = orderId;
        this.batteryId = batteryId;
        this.organizationName = organizationName;
        this.mark = mark;
        this.capacity = capacity;
        this.amperage = amperage;
        this.namePolarity = namePolarity;
        this.quantityProduct = quantityProduct;
        this.dateExecution = dateExecution;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getMark() {
        return mark;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAmperage() {
        return amperage;
    }

    public String getNamePolarity() {
        return namePolarity;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public Date getDateExecution() {
        return dateExecution;
    }
}
