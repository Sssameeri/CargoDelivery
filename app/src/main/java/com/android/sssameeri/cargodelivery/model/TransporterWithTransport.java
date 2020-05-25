package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;

public class TransporterWithTransport {
    @ColumnInfo(name = "transporter_id")
    private long id;
    @ColumnInfo(name = "transporter_name")
    private String name;
    @ColumnInfo(name = "transporter_phone")
    private String phone;
    @ColumnInfo(name = "transport_name")
    private String transportName;

    public TransporterWithTransport(long id, String name, String phone, String transportName) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.transportName = transportName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }


    @Override
    public String toString() {
        return "TransporterAndTransports{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", transportName='" + transportName + '\'' +
                '}';
    }
}
