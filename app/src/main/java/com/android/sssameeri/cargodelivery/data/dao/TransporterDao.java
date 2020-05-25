package com.android.sssameeri.cargodelivery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.sssameeri.cargodelivery.model.Transporter;
import com.android.sssameeri.cargodelivery.model.TransporterWithTransport;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TransporterDao {
    @Insert
    Single<Long> insertTransporter(Transporter transporter);

    @Query("SELECT * FROM transporter INNER JOIN transport ON transporter.transport_id = transport.transport_id WHERE transporter_id = :id")
    Single<TransporterWithTransport> getTransporterById(long id);

    @Query("SELECT * FROM transporter WHERE transporter_phone = :phone AND transporter_password = :password")
    Single<Transporter> getTransporterData(String phone, String password);
}
