package com.android.sssameeri.cargodelivery.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transport")
public class Transport {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transport_id")
    private long id;
    @ColumnInfo(name = "transport_name")
    private String name;

    public Transport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Transport(String name) {
        this.name = name;
    }

    @Ignore
    public Transport() {
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

    @Override
    public String toString() {
        return name;
    }
}