package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;

public class OrderWithTransporterData {
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
    @ColumnInfo(name = "order_description")
    private String orderDescription;
    @ColumnInfo(name = "transporter_name")
    private String transporterName;
    @ColumnInfo(name = "order_price")
    private double price;
    @ColumnInfo(name = "transporter_phone")
    private String transporterPhone;

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

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTransporterPhone() {
        return transporterPhone;
    }

    public void setTransporterPhone(String transporterPhone) {
        this.transporterPhone = transporterPhone;
    }
}
