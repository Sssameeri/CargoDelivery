package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders",
        foreignKeys = {
                @ForeignKey(entity = Customer.class,
                        parentColumns = "customer_id",
                        childColumns = "customer_id"),
                @ForeignKey(entity = Transporter.class,
                        parentColumns = "transporter_id",
                        childColumns = "transporter_id"),
                @ForeignKey(entity = Transport.class,
                        parentColumns = "transport_id",
                        childColumns = "transport_id"
                )
        })
public class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    private long id;
    @ColumnInfo(name = "address_from")
    private String addressFrom;
    @ColumnInfo(name = "address_to")
    private String addressTo;
    @ColumnInfo(name = "city_from")
    private String cityFrom;
    @ColumnInfo(name = "city_to")
    private String cityTo;
    @ColumnInfo(name = "date_start")
    private String dateFrom;
    @ColumnInfo(name = "date_end")
    private String dateTo;
    @ColumnInfo(name = "order_status")
    private String status;
    @ColumnInfo(name = "order_description")
    private String orderDescription;
    @ColumnInfo(name = "customer_id")
    private long idCustomer;
    @ColumnInfo(name = "transporter_id")
    private Long idTransporter;
    @ColumnInfo(name = "transport_id")
    private long idTransport;
    @ColumnInfo(name = "order_price")
    private double price;

    @Ignore
    public Order(long id,
                 String addressFrom,
                 String addressTo,
                 String cityFrom,
                 String cityTo,
                 String dateFrom,
                 String dateTo,
                 String status,
                 String orderDescription,
                 long idCustomer,
                 Long idTransporter,
                 long idTransport,
                 double price) {
        this.id = id;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
        this.orderDescription = orderDescription;
        this.idCustomer = idCustomer;
        this.idTransporter = idTransporter;
        this.idTransport = idTransport;
        this.price = price;
    }

    public Order(String addressFrom,
                 String addressTo,
                 String cityFrom,
                 String cityTo,
                 String dateFrom,
                 String dateTo,
                 String status,
                 String orderDescription,
                 long idCustomer,
                 long idTransport
                 ) {
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.status = status;
        this.orderDescription = orderDescription;
        this.idCustomer = idCustomer;
        this.idTransport = idTransport;
    }

    @Ignore
    public Order() {}

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdTransporter() {
        return idTransporter;
    }

    public void setIdTransporter(Long idTransporter) {
        this.idTransporter = idTransporter;
    }

    public long getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(long idTransport) {
        this.idTransport = idTransport;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}