package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    private long id;
    @ColumnInfo(name = "customer_name")
    private String name;
    @ColumnInfo(name = "customer_phone")
    private String phone;
    @ColumnInfo(name = "customer_password")
    private String password;

    public Customer(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Customer(long id, String name, String phone, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public Customer() {
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
