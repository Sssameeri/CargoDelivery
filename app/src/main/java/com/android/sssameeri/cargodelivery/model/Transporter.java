package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "transporter",
        foreignKeys =
        @ForeignKey(entity = Transport.class,
                parentColumns = "transport_id",
                childColumns = "transport_id")
)
public class Transporter {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transporter_id")
    private long id;
    @ColumnInfo(name = "transporter_name")
    private String name;
    @ColumnInfo(name = "transporter_phone")
    private String phone;
    @ColumnInfo(name = "transporter_password")
    private String password;
    @ColumnInfo(name = "transport_id")
    private long idTransport;

    public Transporter(long id, String name, String phone, String password, long idTransport) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.idTransport = idTransport;
    }

    public Transporter(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public Transporter() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(long idTransport) {
        this.idTransport = idTransport;
    }
}