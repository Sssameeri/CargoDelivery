package com.android.sssameeri.cargodelivery.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.sssameeri.cargodelivery.data.database.Database;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.model.Transporter;
import com.android.sssameeri.cargodelivery.model.TransporterWithTransport;
import com.android.sssameeri.cargodelivery.repository.Repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TransporterViewModel extends AndroidViewModel {

    private Repository repository;

    private MutableLiveData<Long> transporterId = new MutableLiveData<>();

    public TransporterViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Single<Long> insertTransporter(Transporter transporter) {
        return repository.insertTransporter(transporter);
    }

    public Flowable<List<Transport>> getAllTransport() {
        return repository.getAllTransport();
    }

    public Single<TransporterWithTransport> getTransporterById(long id) {
        return repository.getTransporterById(id);
    }

    public Single<Transporter> getTransporterData(String phone, String password) {
        return repository.getTransporterData(phone, password);
    }

    //LiveData
    public void setId(long id) {
        transporterId.postValue(id);
    }
    public LiveData<Long> getId() {
        return transporterId;
    }

    //Close database
    public void closeDatabase() {
        repository.closeDatabase();
    }
}
