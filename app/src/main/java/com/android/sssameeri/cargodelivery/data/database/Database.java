package com.android.sssameeri.cargodelivery.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.sssameeri.cargodelivery.data.dao.CustomerDao;
import com.android.sssameeri.cargodelivery.data.dao.OrderDao;
import com.android.sssameeri.cargodelivery.data.dao.TransportDao;
import com.android.sssameeri.cargodelivery.data.dao.TransporterDao;
import com.android.sssameeri.cargodelivery.model.Customer;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.model.Transporter;

@androidx.room.Database(version = 1, entities = {Customer.class, Transport.class, Transporter.class, Order.class})
public abstract class Database extends RoomDatabase {

    public abstract CustomerDao getCustomerDao();
    public abstract TransportDao getTransportDao();
    public abstract TransporterDao getTransporterDao();
    public abstract OrderDao getOrderDao();

    private static Database instance;

    public static Database getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class,
                    "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void closeDatabase() {
        instance.close();
    }
}