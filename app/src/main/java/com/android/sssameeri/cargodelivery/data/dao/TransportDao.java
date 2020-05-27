package com.android.sssameeri.cargodelivery.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.sssameeri.cargodelivery.model.Transport;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TransportDao {
    @Insert
    Completable insertTransports(List<Transport> transport);

    @Query("SELECT * FROM transport")
    Flowable<List<Transport>> getAllTransport();
}
