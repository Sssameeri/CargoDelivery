package com.android.sssameeri.cargodelivery.viewmodel;

import android.app.Application;
import android.service.autofill.SaveCallback;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.android.sssameeri.cargodelivery.model.Customer;
import com.android.sssameeri.cargodelivery.model.Order;
import com.android.sssameeri.cargodelivery.model.Transport;
import com.android.sssameeri.cargodelivery.repository.Repository;

import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends AndroidViewModel {

    //Repository reference
    private Repository repository;

    //LiveDataVariables
    private MutableLiveData<String> message = new MutableLiveData<>();
    private MutableLiveData<Long> customerId = new MutableLiveData<>();

    private List<Transport> allTransport;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    //Insert customer
    public Single<Long> insertCustomer(Customer customer) {
        return repository.insertCustomer(customer);
    }

    //Customer data
    public Single<Customer> getCustomerById(long id) {
        return repository.getCustomerDataById(id);
    }
    public Single<Customer> getCustomerData(String phone, String password) { return repository.getCustomerData(phone, password);}

    //Create order
    public Single<Long> insertOrder(Order order) {return repository.insertOrder(order);}

    //Get customer order by status
    public Flowable<List<Order>> getCustomerOrdersByStatus(long id, String status) {
        return repository.getCustomerOrdersByStatus(id, status);
    }

    public Flowable<List<Transport>> getAllTransport() {
        return repository.getAllTransport();
    }

    //LiveData
    public void setId(long id) {
        customerId.postValue(id);
    }
    public LiveData<Long> getId() {
        return customerId;
    }

    //Close database
    public void closeDatabase() {
        repository.closeDatabase();
    }
}
