package com.android.sssameeri.cargodelivery.data.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.sssameeri.cargodelivery.data.dao.CustomerDao;
import com.android.sssameeri.cargodelivery.data.dao.OrderDao;
import com.android.sssameeri.cargodelivery.data.dao.TransportDao;
import com.android.sssameeri.cargodelivery.data.dao.TransporterDao;
import com.android.sssameeri.cargodelivery.model.Customer;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.model.Transporter;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@androidx.room.Database(version = 1, entities = {Customer.class, Transport.class, Transporter.class, Order.class})
public abstract class Database extends RoomDatabase {

    public abstract CustomerDao getCustomerDao();
    public abstract TransportDao getTransportDao();
    public abstract TransporterDao getTransporterDao();
    public abstract OrderDao getOrderDao();

    private static List<Transport> transports = new ArrayList<>();

    private static Database instance;

    public static Database getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class,
                    "database")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Disposable disposable = getInstance(context).getTransportDao().insertTransports(transportList())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            () -> {
                                                Log.d("TAG", "inserterd");
                                            }, throwable -> {
                                                Log.d("TAG", throwable.getMessage());
                                            }
                                    );

                        }
                    })
                    .build();
        }
        return instance;
    }

    public void closeDatabase() {
        instance.close();
    }

    public static List<Transport> transportList() {
        transports.add(new Transport("Велосипед"));
        transports.add(new Transport("Легкова машина"));
        transports.add(new Transport("Пікап"));
        transports.add(new Transport("Мотоцикл"));
        transports.add(new Transport("Вантажівка"));
        return transports;
    }

}